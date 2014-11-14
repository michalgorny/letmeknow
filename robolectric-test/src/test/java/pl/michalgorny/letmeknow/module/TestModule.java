package pl.michalgorny.letmeknow.module;

import dagger.Module;
import pl.michalgorny.letmeknow.GameManagerTest;

/**
 * Test module for testing purpose
 *
 * @author m.gorny
 */

@Module(
        includes = {MockApiModule.class},
        injects = GameManagerTest.class,
        overrides = true
)


public class TestModule {

}
