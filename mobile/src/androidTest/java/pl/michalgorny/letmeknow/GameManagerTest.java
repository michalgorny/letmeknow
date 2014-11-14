package pl.michalgorny.letmeknow;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import javax.inject.Inject;

import dagger.ObjectGraph;
import pl.michalgorny.letmeknow.dagger.modules.AppModule;
import pl.michalgorny.letmeknow.managers.GameManager;
import pl.michalgorny.letmeknow.module.TestModule;

import static org.junit.Assert.*;

/**
 * Test class for Game manager
 *
 * @author m.gorny
 */
@RunWith(RobolectricTestRunner.class)
public class GameManagerTest {
    @Inject
    GameManager mGameManager;

    @Before
    public void setUp() throws Exception {
        ObjectGraph.create(TestModule.class, new AppModule(new LetMeKnowApplication())).inject(this);
    }

    public void testPreconditions() throws Exception {
        assertNotNull(mGameManager);
    }

    public void testStartGame() throws Exception {
        mGameManager.startGame();
        assertEquals(GameManager.GameState.GAME, mGameManager.getGameState());
    }

    public void tearDown() throws Exception {
        mGameManager.reset();
    }

}
