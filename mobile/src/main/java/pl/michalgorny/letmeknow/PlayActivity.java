package pl.michalgorny.letmeknow;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.michalgorny.letmeknow.events.StartGameEvent;
import pl.michalgorny.letmeknow.events.TableAvailableEvent;


public class PlayActivity extends Activity {

    @InjectView(R.id.game_start_game)
    Button mStartGame;

    @InjectView(R.id.game_let_know)
    Button mLetKnow;

    @Inject
    public Bus mBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.inject(this);
        ((LetMeKnowApplication)getApplication()).mGraph.inject(this);
        mBus.register(this);
    }

    @OnClick(R.id.game_start_game)
    void startGame(){
        Toast.makeText(this, "start game", Toast.LENGTH_SHORT).show();
        mBus.post(new StartGameEvent());
    }

    @OnClick(R.id.game_let_know)
    void getOtherKnow(){
        Toast.makeText(this, "let know", Toast.LENGTH_SHORT).show();
        mBus.post(new TableAvailableEvent());
    }
}
