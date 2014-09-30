package pl.michalgorny.letmeknow.ui;

import android.os.Bundle;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.michalgorny.letmeknow.R;
import pl.michalgorny.letmeknow.events.GameManager;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.game_start_game)
    Button mStartGame;

    @Inject
    GameManager mGameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.game_start_game)
    void startGame(){
        mGameManager.startGame();
        switchActivity(PlayActivity.class);
    }

}
