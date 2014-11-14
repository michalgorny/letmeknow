package pl.michalgorny.letmeknow.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.michalgorny.letmeknow.LetMeKnowApplication;
import pl.michalgorny.letmeknow.api.parse.ParseRequestInterceptor;
import pl.michalgorny.letmeknow.gcm.PushReceiver;
import pl.michalgorny.letmeknow.managers.JsonParser;
import pl.michalgorny.letmeknow.managers.ParsePushManager;
import pl.michalgorny.letmeknow.ui.MainActivity;
import pl.michalgorny.letmeknow.utils.Utils;

/**
 * Application module for satisfy all main application dependencies. It includes another modules to
 * satisfy specialized dependencies.
 *
 * @author m.gorny
 */

@Module(
        includes = {
                ApiModule.class,
        },
        injects = {
                MainActivity.class,
                ParseRequestInterceptor.class,
                ParsePushManager.class,
                PushReceiver.class
        },
        staticInjections = {
                Utils.class
        }
)
public class AppModule {
    private final LetMeKnowApplication mApplication;

    public AppModule(Application application) {
        mApplication = (LetMeKnowApplication) application;
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus(ThreadEnforcer.ANY);
    }

    @Provides
    @Singleton
    public LetMeKnowApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public JsonParser provideJsonParser() {
        return new JsonParser();
    }

}