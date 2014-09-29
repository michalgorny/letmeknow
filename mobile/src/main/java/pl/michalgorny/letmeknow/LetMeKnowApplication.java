package pl.michalgorny.letmeknow;

import android.app.Application;

import com.squareup.otto.Bus;

import dagger.ObjectGraph;
import pl.michalgorny.letmeknow.dagger.modules.AppModule;

public class LetMeKnowApplication extends Application {

    public ObjectGraph mGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraph();
    }

    private void buildObjectGraph() {
        mGraph = ObjectGraph.create(new AppModule());
        mGraph.inject(this);
    }
}