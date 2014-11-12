package pl.michalgorny.letmeknow.events;

/**
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
