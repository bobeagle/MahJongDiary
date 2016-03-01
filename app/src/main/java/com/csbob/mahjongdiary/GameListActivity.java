package com.csbob.mahjongdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.csbob.mahjongdiary.adaptor.GameListAdaptor;
import com.csbob.mahjongdiary.controller.AppController;
import com.csbob.mahjongdiary.model.Game;
import com.csbob.mahjongdiary.model.IntentExtraKey;

public class GameListActivity extends AppCompatActivity {

    private ArrayAdapter<Game> adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame(view);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.game_list);
        adaptor = new GameListAdaptor(this);
        listView.setAdapter(adaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {

                Log.i(GameListActivity.class.getName(), "onClick at position " + position);

                view.animate().setDuration(1000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(GameListActivity.class.getName(), "removing " + parent.getItemAtPosition(position));
                            }
                        });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewGame(View view) {
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Game newGame = (Game) data.getExtras().getSerializable(IntentExtraKey.RESULT.name());
            Log.i(this.getLocalClassName(), "Got game from newGameActivity " + newGame);
            AppController.getInstance().addGame(newGame);
            adaptor.notifyDataSetChanged();
        }
    }
}
