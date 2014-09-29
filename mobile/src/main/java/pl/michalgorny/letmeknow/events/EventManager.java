package pl.michalgorny.letmeknow.events;

import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import pl.michalgorny.letmeknow.events.TableAvailableEvent;

public class EventManager {

    @Inject
    Bus mBus;

    public EventManager() {
        mBus.register(this);
    }

    @Subscribe
    public void tableAvailable(TableAvailableEvent event){
        Log.d("EVENT manager", "table available");
    }

    @Subscribe
    public void gameStarted(StartGameEvent event){
        Log.d("EVENT manager", "start game");
    }

}
