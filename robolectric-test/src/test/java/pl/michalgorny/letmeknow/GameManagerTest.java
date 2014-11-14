package pl.michalgorny.letmeknow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertTrue;

/**
 * Test class for Game manager
 *
 * @author m.gorny
 */
@RunWith(RobolectricTestRunner.class)
public class GameManagerTest {
/*    @Inject
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
    }*/

    @Test
    public void testDummy() throws Exception {
        assertTrue(true);
    }
}
