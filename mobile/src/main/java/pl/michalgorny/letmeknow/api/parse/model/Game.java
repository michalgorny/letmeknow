package pl.michalgorny.letmeknow.api.parse.model;

import com.parse.ParseClassName;

/**
 * POJO class describing a game
 * @author m.gorny
 */
@ParseClassName("Game")
public class Game {

    String objectId;

    String name;

    public Game(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.objectId = id;
    }

    public String getId() {
        return objectId;
    }

    public String getName() {
        return name;
    }
}
