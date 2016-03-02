package com.csbob.mahjongdiary.adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csbob.mahjongdiary.R;
import com.csbob.mahjongdiary.model.Game;
import com.csbob.mahjongdiary.model.Score;

/**
 * List View Adaptor for game edit page - list of scores
 * Created by yiyang on 29/02/2016.
 */
public class ScoreListAdaptor extends ArrayAdapter<Score> {

    private final Activity context;
    private final Game game;

    public ScoreListAdaptor(Context context, Game game) {
        super(context, -1);
        this.context = (Activity) context;
        this.game = game;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = context.getLayoutInflater().inflate(R.layout.row_score_list, parent, false);
            viewHolder.cell = (TextView) convertView.findViewById(R.id.score_list_cell);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Score score = game.scores.get(position);
        String content = String.format("%s %s %s %s %s=%d", score.winner, context.getResources().getString(R.string.win),
                score.loser, context.getResources().getString(R.string.loss),
                context.getResources().getString(R.string.exp), score.exp
        );

        viewHolder.cell.setText(content);

        return convertView;
    }

    @Override
    public int getCount() {
        return game.scores.size();
    }

    @Override
    public Score getItem(int position) {
        return game.scores.get(position);
    }

    static class ViewHolder {
        TextView cell;
    }
}
