package pl.michalgorny.letmeknow.events;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.michalgorny.letmeknow.LetMeKnowApplication;
import pl.michalgorny.letmeknow.utils.Utils;
import timber.log.Timber;

@Singleton
public class GameManager {

    private Bus mBus;

    private RestService mRestService;

    @Inject
    public GameManager(RestService service, Bus bus) {
        mRestService = service;
        mBus = bus;
    }

    public void startGame() {
        Utils.makeToast("StartGame");
//        mRestService.startNewGame();

    }
}
