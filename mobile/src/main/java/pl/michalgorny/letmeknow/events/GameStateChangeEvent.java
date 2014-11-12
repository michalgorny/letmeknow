package pl.michalgorny.letmeknow.events;

import pl.michalgorny.letmeknow.managers.GameManager;

/**
 * Event created when a game state has been changed
 * @author m.gorny
 */
public class GameStateChangeEvent {

    private GameManager.GameState state;

    public GameStateChangeEvent(GameManager.GameState state) {
        this.state = state;
    }

    public GameManager.GameState getState() {
        return state;
    }
}
