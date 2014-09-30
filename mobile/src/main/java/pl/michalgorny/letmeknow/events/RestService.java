package pl.michalgorny.letmeknow.events;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author m.gorny
 */
public interface RestService {

    @GET("/newgame")
    void startNewGame(Callback callback);
}
