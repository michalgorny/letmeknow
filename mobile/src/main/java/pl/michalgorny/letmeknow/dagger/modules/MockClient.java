package pl.michalgorny.letmeknow.dagger.modules;

import java.io.IOException;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * @author m.gorny
 */
public class MockClient implements Client {

    private static final String CREATE_GAME_VALID_JSON = "{\n" +
            "    \"createdAt\": \"2014-11-04T14:15:10.976Z\",\n" +
            "    \"objectId\": \"Q0t7hlc42M\"\n" +
            "}";
    private static final int HTTP_STATUS_CREATED = 201;

    @Override
    public Response execute(Request request) throws IOException {
        return createResponseWithCodeAndJson(HTTP_STATUS_CREATED, CREATE_GAME_VALID_JSON);
    }

    private Response createResponseWithCodeAndJson(int responseCode, String json) {
        return new Response("url", responseCode, "nothing", Collections.EMPTY_LIST, new TypedByteArray("application/json", json.getBytes()));

    }
}
