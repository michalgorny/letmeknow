package pl.michalgorny.letmeknow.gcm;

import com.google.gson.annotations.SerializedName;

/**
 * @author m.gorny
 */
public class PushMessage {

    @SerializedName("action")
    PushAction action;

    @SerializedName("data")
    Object data;

    public PushMessage(PushAction action, Object data) {
        this.action = action;
        this.data = data;
    }

    public PushAction getAction() {
        return action;
    }

    public Object getData() {
        return data;
    }

}
