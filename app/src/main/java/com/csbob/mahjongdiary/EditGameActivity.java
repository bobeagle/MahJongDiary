package com.csbob.mahjongdiary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.csbob.mahjongdiary.adaptor.ScoreListAdaptor;
import com.csbob.mahjongdiary.controller.AppController;
import com.csbob.mahjongdiary.model.Game;
import com.csbob.mahjongdiary.model.IntentExtraKey;
import com.csbob.mahjongdiary.model.Score;

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
    private ArrayAdapter<Score> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);

        int position = getIntent().getIntExtra(IntentExtraKey.PAYLOAD.name(), -1);
        if (position < 0)
            finish();
        else
            game = AppController.getInstance().getGames().get(position);

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

        winner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playerList.toArray()));
        playerList.add(getResources().getString(R.string.self));
        loser.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playerList.toArray()));

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

        adapter = new ScoreListAdaptor(this, game);
        ListView listView = (ListView) findViewById(R.id.score_list);
        listView.setAdapter(adapter);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String winnerStr = (String) winner.getSelectedItem();
                String loserStr = (String) loser.getSelectedItem();
                int exp = onExpSelection();
                Score score = new Score(winnerStr, loserStr, exp);
                game.addScore(score);
                adapter.notifyDataSetChanged();
                showResults();
            }
        });
    }

    private int onExpSelection() {
        int exp = game.maxExp * expBar.getProgress() / 100;
        exp = exp > 0 ? exp : 1;
        String expStr = getResources().getString(R.string.exp);
        expLabel.setText(expStr + ": " + exp);
        return exp;
    }

    private void showResults() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(true);

        eastLabel.setText(game.east + ": " + df.format(game.eastResult));
        westLabel.setText(game.west + ": " + df.format(game.westResult));
        southLabel.setText(game.south + ": " + df.format(game.southResult));
        northLabel.setText(game.north + ": " + df.format(game.northResult));
    }
}
