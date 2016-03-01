package com.csbob.mahjongdiary.adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csbob.mahjongdiary.R;
import com.csbob.mahjongdiary.controller.AppController;
import com.csbob.mahjongdiary.model.Game;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * List View Adaptor for home page - list of games
 * Created by yiyang on 29/02/2016.
 */
public class GameListAdaptor extends ArrayAdapter<Game> {

    private final Activity context;

    public GameListAdaptor(Context context) {
        super(context, -1);
        this.context = (Activity) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = context.getLayoutInflater().inflate(R.layout.row_game_list, parent, false);
            viewHolder.headerView = (TextView) convertView.findViewById(R.id.game_list_row_header);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.game_list_row_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Game game = AppController.getInstance().getGames().get(position);

        String header = String.format(Locale.UK, "%s %s %s %s", game.east, game.south, game.west, game.north);
        String text = new SimpleDateFormat("yyyy-MM-dd", Locale.UK).format(game.playDate);

        viewHolder.headerView.setText(header);
        viewHolder.textView.setText(text);

        return convertView;
    }

    @Override
    public int getCount() {
        return AppController.getInstance().getGames().size();
    }

    @Override
    public Game getItem(int position) {
        return AppController.getInstance().getGames().get(position);
    }

    static class ViewHolder {
        TextView headerView;
        TextView textView;
    }
}
