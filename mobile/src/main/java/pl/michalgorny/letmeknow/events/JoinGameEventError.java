package pl.michalgorny.letmeknow.events;

/**
 * Event created when error occurred during change game state to indicate appropriate behaviour
 * @author m.gorny
 */
public class JoinGameEventError {
    String message;

    public JoinGameEventError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
