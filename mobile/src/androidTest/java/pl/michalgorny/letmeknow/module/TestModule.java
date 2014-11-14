package pl.michalgorny.letmeknow.module;

import dagger.Module;
import pl.michalgorny.letmeknow.GameManagerTest;
import pl.michalgorny.letmeknow.dagger.modules.AppModule;

/**
 * Test module for testing purpose
 *
 * @author m.gorny
 */

@Module(
        includes = AppModule.class,
        injects = GameManagerTest.class,
        overrides = true
)
public class TestModule {

}
