package pl.michalgorny.letmeknow.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import pl.michalgorny.letmeknow.LetMeKnowApplication;

/**
 * @author m.gorny
 */
public class BaseActivity extends Activity {

    @Inject
    public Bus mBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LetMeKnowApplication.daggerInject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBus.unregister(this);
    }

    public void switchActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
