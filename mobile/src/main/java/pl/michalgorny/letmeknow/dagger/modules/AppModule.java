package pl.michalgorny.letmeknow.dagger.modules;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import pl.michalgorny.letmeknow.LetMeKnowApplication;
import pl.michalgorny.letmeknow.events.GameManager;
import pl.michalgorny.letmeknow.ui.MainActivity;
import pl.michalgorny.letmeknow.ui.PlayActivity;
import pl.michalgorny.letmeknow.utils.Utils;

@Module(
        includes = {
          ApiModule.class
        },
        injects = {
                PlayActivity.class,
                MainActivity.class,
//                GameManager.class
        },
        staticInjections = {
                Utils.class
        }
)
public class AppModule {
    private static ObjectGraph mObjectGraph;
    private final LetMeKnowApplication mApplication;

    public AppModule(LetMeKnowApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Bus provideBus(){
        return new Bus(ThreadEnforcer.ANY);
    }

    @Provides
    @Singleton
    public LetMeKnowApplication provideApplication(){
        return mApplication;
    }

}