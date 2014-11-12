package pl.michalgorny.letmeknow.managers;

import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.parse.SendCallback;
import com.squareup.otto.Bus;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.michalgorny.letmeknow.api.parse.model.Game;
import pl.michalgorny.letmeknow.events.GameFinishedEvent;
import pl.michalgorny.letmeknow.gcm.PushAction;
import pl.michalgorny.letmeknow.gcm.PushMessage;
import pl.michalgorny.letmeknow.utils.Utils;

/**
 * Push message manager used Parse service. Responsible for creating and handling messages.
 * @author m.gorny
 */

@Singleton
public class ParsePushManager {

    @Inject
    JsonParser jsonParser;

    @Inject
    Bus mBus;

    public void sendPushMessage(PushAction action, Object data, String channel, SendCallback callback) throws JSONException {
        ParsePush push = new ParsePush();
        push.setChannel(channel);

        PushMessage message = new PushMessage(action, data);

        push.setData(new JSONObject(jsonParser.toJson(message)));
        push.sendInBackground(callback);
    }

    public void subscribeChannel(String channel, SaveCallback callback) {
        ParsePush.subscribeInBackground(channel, callback);
    }

    public void handleReceivedMessage(PushMessage message){
        PushAction action = message.getAction();
        if (action == PushAction.GAME_FINISHED){

            mBus.post(new GameFinishedEvent());

            Game game = jsonParser.fromJson(message.getData().toString(), Game.class);
            Utils.makeToast(game.getId());
        }
    }
}
