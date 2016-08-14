/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          ChoiceGridAdapter                                   |
|   Description:    ChoiceGridAdapter class.                            |
|                                                                       |
|                                                                       |
|                                                                       |
|   Author:         Zayd-Waves                                          |
|   Date:           5/31/2016                                           |
|                                                                       |
|                                                                       |
|                                                                       |
 -----------------------------------------------------------------------
*/
package me.zaydbille.pokedex.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import me.zaydbille.pokedex.R;

public class ChoiceGridAdapter extends BaseAdapter {

    private Context                             context;
    private final String[]                      choices;
    private ArrayList<TextView>                 texts;

    public ChoiceGridAdapter(Context context, String[] choices) {
        this.context = context;
        this.choices = choices;
        texts = new ArrayList<>();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {

            gridView = new View(context);
            gridView = inflater.inflate(R.layout.choice_grid_layout, null);

            /* Set the TextView value. */
            TextView textView = (TextView) gridView.findViewById(R.id.choiceText);
            if(position == 0){
                textView.setTypeface(null, Typeface.BOLD);
            }
            textView.setText(choices[position]);
            texts.add(textView);

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

    public void clearTexts() {
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i) != null) {
                texts.get(i).setTypeface(null, Typeface.NORMAL);
            }
        }
    }
}