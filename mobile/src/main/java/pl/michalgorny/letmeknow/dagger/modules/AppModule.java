package pl.michalgorny.letmeknow.dagger.modules;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.michalgorny.letmeknow.LetMeKnowApplication;
import pl.michalgorny.letmeknow.PlayActivity;

@Module(
        injects = {
                PlayActivity.class,
                LetMeKnowApplication.class
        }
)
public class AppModule {
    @Provides
    @Singleton
    public Bus provideBus(){
        return new Bus();
    }
}