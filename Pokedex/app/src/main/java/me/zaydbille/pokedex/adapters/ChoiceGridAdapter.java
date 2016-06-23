package me.zaydbille.pokedex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.data.Pokemon;

/**
 * Created by Zayd on 6/20/16.
 */
public class ChoiceGridAdapter extends BaseAdapter {

    private Context context;
    private final String[] choices;

    public ChoiceGridAdapter(Context context, String[] choices) {
        this.context = context;
        this.choices = choices;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.choice_grid_layout, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.choiceText);
            textView.setText(choices[position]);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return choices.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}