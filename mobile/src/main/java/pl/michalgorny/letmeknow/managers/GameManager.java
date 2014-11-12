package pl.michalgorny.letmeknow.managers;

import com.parse.ParseException;
import com.parse.SaveCallback;
import com.parse.SendCallback;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;

import org.json.JSONException;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.michalgorny.letmeknow.api.parse.ParseService;
import pl.michalgorny.letmeknow.api.parse.model.Game;
import pl.michalgorny.letmeknow.events.GameStateChangeEvent;
import pl.michalgorny.letmeknow.events.JoinGameEventError;
import pl.michalgorny.letmeknow.gcm.PushAction;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

@Singleton
public class GameManager {

    private static final String GAME_NAME = "football";

    @Inject
    ParseService mParseService;

    @Inject
    ParsePushManager mParsePushManager;

    private Bus mBus;

    private Game mCurrentGame;
    private GameState mGameState = GameState.READY;

    @Inject
    public GameManager(Bus bus) {
        mBus = bus;
        mBus.register(this);
    }

    public void startGame() {
        Timber.d("Starting new game");
        setGameState(GameState.PROGRESS);
        mParseService.postStartNewGame(new Game(GAME_NAME), new Callback<Game>() {

            @Override
            public void success(Game game, Response response) {
                Timber.d("Success! Id: " + game.getId() + " Response: " + response.getBody().toString());
                mCurrentGame = game;
                addGameSubscription(mCurrentGame.getId());
            }

            @Override
            public void failure(RetrofitError error) {
                handleError(error);
            }
        });
    }

    private void addGameSubscription(final String gameId) {
        mParsePushManager.subscribeChannel(gameId, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    handleError(e);
                } else {
                    Timber.d("Subscription added to game: " + gameId);
                    setGameState(GameState.GAME);
                }
            }
        });
    }

    public void finishGame() {
        Timber.d("Finish game");
        setGameState(GameState.READY);
        setGameState(GameState.PROGRESS);

        try {
            mParsePushManager.sendPushMessage(PushAction.GAME_FINISHED, getCurrentGame(), getCurrentGame().getId(), new SendCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        handleError(e);
                    }
                    else {
                        Timber.d("Push was sent successfully");
                        setGameState(GameState.READY);
                    }
                }
            });
        } catch (JSONException e) {
            handleError(e);
        }
    }

    public void reset() {
        Timber.d("Reset game");
        mCurrentGame = null;
        setGameState(GameState.READY);
    }

    @Produce
    public GameStateChangeEvent lastState(){
        Timber.d("Returning last state");
        return new GameStateChangeEvent(getGameState());
    }

    public Game getCurrentGame() {
        return mCurrentGame;
    }

    public void setGameState(GameState state) {
        Timber.d("Game state changed: " + state);
        mGameState = state;
        mBus.post(new GameStateChangeEvent(state));
    }

    public GameState getGameState() {
        return mGameState;
    }

    private void handleError(Exception exception){
        String message = "Exception occured! " + exception.getMessage();
        Timber.e(message);
        mBus.post(new JoinGameEventError(message));
        setGameState(GameState.ERROR);
    }

    public enum GameState {
        READY, ERROR, GAME, PROGRESS;
    }
}
