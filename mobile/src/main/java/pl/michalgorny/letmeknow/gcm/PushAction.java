package pl.michalgorny.letmeknow.gcm;

/**
 * @author m.gorny
 */
public enum PushAction {
    GAME_FINISHED("game_finished");

    String action;

    PushAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return action;
    }
}

