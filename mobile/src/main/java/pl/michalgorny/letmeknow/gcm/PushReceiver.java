package pl.michalgorny.letmeknow.gcm;

import android.content.Context;
import android.content.Intent;

import com.parse.ParsePushBroadcastReceiver;

import javax.inject.Inject;

import pl.michalgorny.letmeknow.LetMeKnowApplication;
import pl.michalgorny.letmeknow.managers.JsonParser;
import pl.michalgorny.letmeknow.managers.ParsePushManager;
import pl.michalgorny.letmeknow.ui.MainActivity;
import timber.log.Timber;

/**
 * Extension of @ParsePushBroadcastReceiver to handle receiving Push messages
 * @author m.gorny
 */
public class PushReceiver extends ParsePushBroadcastReceiver {

    private static final String PARSE_EXTRA_DATA_KEY = "com.parse.Data";

    @Inject
    ParsePushManager parsePushManager;

    @Inject
    JsonParser jsonParser;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.putExtras(intent.getExtras());
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newIntent);
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        Timber.d("onPushReceive");
        super.onPushReceive(context, intent);
        String json = intent.getExtras().getString(PARSE_EXTRA_DATA_KEY);
        Timber.d(json);

        LetMeKnowApplication.daggerInject(this);

        PushMessage message = jsonParser.fromJson(json, PushMessage.class);
        parsePushManager.handleReceivedMessage(message);

    }
}
