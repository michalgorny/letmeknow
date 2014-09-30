package pl.michalgorny.letmeknow.utils;

import android.widget.Toast;

import javax.inject.Inject;

import pl.michalgorny.letmeknow.LetMeKnowApplication;

/**
 * @author m.gorny
 */
public class Utils {
    @Inject
    static LetMeKnowApplication mApplication;

    public static void makeToast(String message){
        Toast.makeText(mApplication, message, Toast.LENGTH_SHORT).show();
    }

}
