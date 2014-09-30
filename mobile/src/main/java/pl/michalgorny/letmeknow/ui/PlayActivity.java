package pl.michalgorny.letmeknow.ui;

import android.os.Bundle;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.michalgorny.letmeknow.R;
import pl.michalgorny.letmeknow.events.GameManager;
import pl.michalgorny.letmeknow.events.TableAvailableEvent;


public class PlayActivity extends BaseActivity {

    @InjectView(R.id.game_let_know)
    Button mLetKnow;

    @Inject
    public GameManager mEventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.game_let_know)
    void getOtherKnow() {
        mBus.post(new TableAvailableEvent());
        finish();
    }

}
