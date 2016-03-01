package com.csbob.mahjongdiary.adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csbob.mahjongdiary.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * List View Adaptor for home page - list of games
 * Created by yiyang on 29/02/2016.
 */
public class GameListAdaptor extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> values;

    public GameListAdaptor(Context context, String[] values) {
        super(context, -1, values);
        this.context = (Activity) context;
        this.values = new ArrayList<>(Arrays.asList(values));
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

        viewHolder.headerView.setText(String.format(Locale.UK, "%d", position));
        viewHolder.textView.setText(values.get(position));

        return convertView;
    }

    @Override
    public void remove(String object) {
        values.remove(object);
    }

    @Override
    public int getCount() {
        return values.size();
    }

    static class ViewHolder {
        TextView headerView;
        TextView textView;
    }
}
