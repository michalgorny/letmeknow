package pl.michalgorny.letmeknow.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.michalgorny.letmeknow.R;
import pl.michalgorny.letmeknow.api.parse.model.Game;
import pl.michalgorny.letmeknow.events.GameStateChangeEvent;
import pl.michalgorny.letmeknow.events.JoinGameEventError;
import pl.michalgorny.letmeknow.managers.GameManager;
import pl.michalgorny.letmeknow.managers.GameManager.GameState;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.game_start_game)
    CircularProgressButton mStartGameButton;

    @InjectView(R.id.game_text_top)
    TextView mTopText;

    @Inject
    GameManager mGameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @InjectView(R.id.editText)
    EditText editText;

    @OnClick(R.id.game_start_game)
    void onButtonClick(){
        final GameState gameState = mGameManager.getGameState();

        if (gameState == GameState.ERROR) {
            mGameManager.reset();
        } else if (gameState == GameState.READY) {
            mGameManager.startGame();
        } else if (gameState == GameState.GAME) {
            mGameManager.finishGame();
        }

    }

    @Subscribe
    public void gameStateChanged(GameStateChangeEvent event) {
        updateUi(event.getState());
    }

    @Subscribe
    public void errorOccured(JoinGameEventError event) {
        setTopText(event.getMessage());
    }

    private void updateUi(GameState state) {
        switch (state) {
            case READY:
                setReadyStatus();
                break;
            case ERROR:
                setErrorStatus();
                break;
            case GAME:
                setPlayingStatus();
                break;
            case PROGRESS:
                setProgressStatus();
                break;
        }

        Timber.d("progress: " + mStartGameButton.getProgress());
    }

    private void setReadyStatus() {
        setButtonStatus(CircularProgressButton.IDLE_STATE_PROGRESS);
        setTopText(getString(R.string.lets_play));
        mStartGameButton.setIdleText(getString(R.string.start_game));
        mStartGameButton.setClickable(true);
    }

    private void setErrorStatus() {
        setButtonStatus(CircularProgressButton.ERROR_STATE_PROGRESS);
        mStartGameButton.setClickable(true);
    }

    private void setPlayingStatus() {
        mStartGameButton.setClickable(false);
        setButtonStatus(CircularProgressButton.SUCCESS_STATE_PROGRESS);

        mStartGameButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                mStartGameButton.setIdleText(getString(R.string.let_know));
                setButtonStatus(CircularProgressButton.IDLE_STATE_PROGRESS);
                mStartGameButton.setClickable(true);
            }
        }, 2000);

        Game game = mGameManager.getCurrentGame();
        setTopText("Your game id: " + game.getId());
    }

    private void setProgressStatus() {
        mStartGameButton.setClickable(false);
        setButtonStatus(CircularProgressButton.INDETERMINATE_STATE_PROGRESS);
        mStartGameButton.setIndeterminateProgressMode(true);
    }

    private void setTopText(String text) {
        mTopText.setText(text);
    }

    private void setButtonStatus(int progress) {
        mStartGameButton.setProgress(progress);
    }

}
