package com.csbob.mahjongdiary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.csbob.mahjongdiary.model.Game;
import com.csbob.mahjongdiary.model.IntentExtraKey;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EditGameActivity extends AppCompatActivity {

    private Game game;

    private Spinner winner;
    private Spinner loser;

    private SeekBar expBar;
    private TextView expLabel;
    private TextView eastLabel;
    private TextView westLabel;
    private TextView southLabel;
    private TextView northLabel;

    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);

        TextView logArea = (TextView) findViewById(R.id.log_area);
        logArea.setMovementMethod(new ScrollingMovementMethod());

        game = (Game) getIntent().getSerializableExtra(IntentExtraKey.PAYLOAD.name());

        // set UI values
        winner = (Spinner) findViewById(R.id.spinner_winner);
        loser = (Spinner) findViewById(R.id.spinner_loser);
        expBar = (SeekBar) findViewById(R.id.bar_win_value);
        expLabel = (TextView) findViewById(R.id.label_winner_exp);
        eastLabel = (TextView) findViewById(R.id.label_east_result);
        westLabel = (TextView) findViewById(R.id.label_west_result);
        southLabel = (TextView) findViewById(R.id.label_south_result);
        northLabel = (TextView) findViewById(R.id.label_north_result);
        saveBtn = (Button) findViewById(R.id.btn_save_score);

        List<String> playerList = new ArrayList<>();
        playerList.add(game.east);
        playerList.add(game.west);
        playerList.add(game.south);
        playerList.add(game.north);

        winner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playerList));
        playerList.add(getResources().getString(R.string.self));
        loser.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playerList));

        expBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onExpSelection();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        onExpSelection();
        showResults();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_done_edit) {
//            String east = ((EditText) findViewById(R.id.input_east)).getText().toString();
//            String west = ((EditText) findViewById(R.id.input_west)).getText().toString();
//            String south = ((EditText) findViewById(R.id.input_south)).getText().toString();
//            String north = ((EditText) findViewById(R.id.input_north)).getText().toString();
//            BigDecimal unitPay = new BigDecimal(((EditText) findViewById(R.id.input_base_chip)).getText().toString());
//            int maxExp = Integer.parseInt(((TextView) findViewById(R.id.label_bar_value)).getText().toString());
//            boolean isSingle = ((ToggleButton) findViewById(R.id.toggle_payer)).isChecked();
//
//            Game newGame = new Game(east.trim(), west.trim(), south.trim(), north.trim(), unitPay, maxExp, isSingle);
//            Log.i(this.getLocalClassName(), "New game created: +" + newGame);
//
//            Intent result = new Intent();
//            result.putExtra(IntentExtraKey.RESULT.name(), newGame);
//            setResult(IntentRequestCode.NEW_GAME.getCode(), result);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onExpSelection() {
        int exp = game.maxExp * expBar.getProgress() / 100;
        exp = exp > 0 ? exp : 1;
        String expStr = getResources().getString(R.string.exp);
        expLabel.setText(expStr + ": " + exp);
    }

    private void showResults() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(true);

        eastLabel.setText(game.east + ": " + df.format(game.eastResult));
        westLabel.setText(game.west + ": " + df.format(game.westResult));
        southLabel.setText(game.south + ": " + df.format(game.southResult));
        northLabel.setText(game.north + ": " + df.format(game.northResult));
    }
}
