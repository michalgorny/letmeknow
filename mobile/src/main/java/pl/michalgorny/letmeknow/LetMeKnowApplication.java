package pl.michalgorny.letmeknow;

import android.app.Application;

import dagger.ObjectGraph;
import pl.michalgorny.letmeknow.dagger.modules.AppModule;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public class LetMeKnowApplication extends Application {

    private static ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new DebugTree());
        objectGraph = ObjectGraph.create(new AppModule(this));
        objectGraph.injectStatics();
    }

    public static void daggerInject(Object o){
        objectGraph.inject(o);
    }

}