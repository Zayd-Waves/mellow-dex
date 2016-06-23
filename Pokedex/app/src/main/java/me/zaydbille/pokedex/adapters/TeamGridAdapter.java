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
 * Created by Zayd on 6/19/16.
 */
public class TeamGridAdapter extends BaseAdapter {

    private Context context;
    private final Pokemon[] pokemon;

    public TeamGridAdapter(Context context, Pokemon[] pokemon) {
        this.context = context;
        this.pokemon = pokemon;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.team_item_layout, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.pokemonText);
            textView.setText(pokemon[position].getName());

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.pokemonImage);
            imageView.setImageResource(R.drawable.p1);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return pokemon.length;
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