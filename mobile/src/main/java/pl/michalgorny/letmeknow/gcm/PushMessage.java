package pl.michalgorny.letmeknow.gcm;

import com.google.gson.annotations.SerializedName;

/**
 * Class to encapsulate Push messages with proper action and data
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
