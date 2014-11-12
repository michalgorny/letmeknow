package pl.michalgorny.letmeknow.api.parse;

import pl.michalgorny.letmeknow.api.parse.model.Game;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * @author m.gorny
 */
public interface ParseService {

    @Headers("Content-Type: application/json")
    @POST("/classes/Game")
    void postStartNewGame(@Body Game game, Callback<Game> callback);

    @GET("/classes/Game/{objectId}")
    void getGame(@Path("objectId") String gameId);

    @GET("/classes/Game")
    void sendFinishGameNotification(Game game, Callback<?> callback);
}
