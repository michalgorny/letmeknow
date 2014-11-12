package pl.michalgorny.letmeknow.managers;

import com.google.gson.Gson;

import javax.inject.Singleton;

/**
 * @author m.gorny
 */

@Singleton
public class JsonParser {
    private Gson gson = new Gson();

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T> T fromJson(String json, Class<T> clazz){
        return gson.fromJson(json, clazz);
    }
}
