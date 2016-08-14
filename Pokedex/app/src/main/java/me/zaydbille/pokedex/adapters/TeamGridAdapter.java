/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          TeamGridAdapter                                     |
|   Description:    TeamGridAdapter class.                              |
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.data.Pokemon;

public class TeamGridAdapter extends BaseAdapter {

    private Context                             context;
    private final Pokemon[]                     pokemon;

    public TeamGridAdapter(Context context, Pokemon[] pokemon) {
        this.context = context;
        this.pokemon = pokemon;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {

            /* Get the layout. */
            gridView = inflater.inflate(R.layout.team_item_layout, null);

            /* Set the TextView's value. */
            TextView textView = (TextView) gridView.findViewById(R.id.pokemonText);
            textView.setText(pokemon[position].getName());

            /* Set image based on the Pokemon. */
            ImageView imageView = (ImageView) gridView.findViewById(R.id.pokemonImage);
            imageView.setImageResource(R.drawable.bulbasaur);

        } else {
            gridView = (View)convertView;
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