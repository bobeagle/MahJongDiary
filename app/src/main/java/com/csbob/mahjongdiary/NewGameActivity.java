package com.csbob.mahjongdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.csbob.mahjongdiary.model.Game;
import com.csbob.mahjongdiary.model.IntentExtraKey;

import java.math.BigDecimal;

public class NewGameActivity extends AppCompatActivity {

    private static final int MAX_EXPONENT = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        final SeekBar seekBar = (SeekBar) findViewById(R.id.bar_top_exp);
        final TextView seedBarValue = (TextView) findViewById(R.id.label_bar_value);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int currentValueInInt = MAX_EXPONENT * progress / 100;
                currentValueInInt = currentValueInInt > 0 ? currentValueInInt : 1;
                seedBarValue.setText(String.valueOf(currentValueInInt));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_create_new_game) {

            String east = ((EditText) findViewById(R.id.input_east)).getText().toString();
            String west = ((EditText) findViewById(R.id.input_west)).getText().toString();
            String south = ((EditText) findViewById(R.id.input_south)).getText().toString();
            String north = ((EditText) findViewById(R.id.input_north)).getText().toString();
            BigDecimal unitPay = new BigDecimal(((EditText) findViewById(R.id.input_base_chip)).getText().toString());
            int maxExp = Integer.parseInt(((TextView) findViewById(R.id.label_bar_value)).getText().toString());
            boolean isSingle = ((ToggleButton) findViewById(R.id.toggle_payer)).isChecked();

            Game newGame = new Game(east.trim(), west.trim(), south.trim(), north.trim(), unitPay, maxExp, isSingle);
            Log.i(this.getLocalClassName(), "New game created: +" + newGame);

            Intent result = new Intent();
            result.putExtra(IntentExtraKey.RESULT.name(), newGame);
            setResult(1, result);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
