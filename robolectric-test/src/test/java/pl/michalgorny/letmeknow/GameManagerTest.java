package pl.michalgorny.letmeknow;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import dagger.ObjectGraph;
import pl.michalgorny.letmeknow.dagger.modules.AppModule;
import pl.michalgorny.letmeknow.managers.GameManager;
import pl.michalgorny.letmeknow.module.TestModule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Game manager
 *
 * @author m.gorny
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class GameManagerTest {
    @Inject
    GameManager mGameManager;

    @Before
    public void setUp() throws Exception {
        ObjectGraph.create(TestModule.class, new AppModule(new LetMeKnowApplication())).inject(this);
    }

    @Test
    public void testPreconditions() throws Exception {
        assertNotNull(mGameManager);
    }

    @Test
    public void testStartGame() throws Exception {
        mGameManager.startGame();
        assertEquals(GameManager.GameState.GAME, mGameManager.getGameState());
    }


    @Test
    public void testDummy() throws Exception {
        assertTrue(true);
    }

    @After
    public void tearDown() throws Exception {
        mGameManager.reset();
    }
}
