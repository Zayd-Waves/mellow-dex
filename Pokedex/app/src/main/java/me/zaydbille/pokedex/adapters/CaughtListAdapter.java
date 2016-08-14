/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          CaughtListAdapter                                   |
|   Description:    Custom list adapter for the CaughtScreen's          |
|                   ListView.                                           |
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

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.storage.PreferencesManager;

public class CaughtListAdapter extends ArrayAdapter<Pokemon> implements android.widget.Filterable {

    private Context                             context;
    private static int                          customLayout = R.layout.caught_list_row;
    private int                                 rowTextColour = 0;
    private int                                 rowColour = 0;
    private List<Pokemon>                       fullList = null;
    private List<Pokemon>                       data = null;
    private AbilityFilter                       mFilter = new AbilityFilter();

    public CaughtListAdapter(Context context, List<Pokemon> data, int textColor, int rowCol) {
        super(context, customLayout, data);
        this.context = context;
        this.data = data;
        rowTextColour = textColor;
        rowColour = rowCol;
        this.fullList = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Pokemon getItem(int position) {
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PokemonHolder holder;
        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(customLayout, parent, false);

            holder = new PokemonHolder();
            holder.pokedexNumber = (TextView) row.findViewById(R.id.pokedexNumber);
            holder.pokemonName = (TextView)row.findViewById(R.id.pokemonName);
            holder.checkmark = (ImageView)row.findViewById(R.id.checkmark);

            row.setTag(holder);

        } else {
            holder = (PokemonHolder) row.getTag();
        }

        Pokemon pokemon = data.get(position);
        int caught = PreferencesManager.checkIfPokemonIsCaught(context, pokemon.getOrderString());

        if(caught == 1) {
            row.setBackgroundColor(ContextCompat.getColor(context, R.color.caught));
            holder.pokedexNumber.setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.pokemonName.setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.checkmark.setVisibility(View.VISIBLE);
        } else {
            row.setBackgroundColor(ContextCompat.getColor(context, rowColour));
            holder.pokedexNumber.setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.pokemonName.setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.checkmark.setVisibility(View.INVISIBLE);
        }
        holder.pokedexNumber.setText(pokemon.getNumberString());
        holder.pokemonName.setText(pokemon.getName());
        return row;
    }

    static class PokemonHolder {
        TextView pokedexNumber;
        TextView pokemonName;
        ImageView checkmark;
    }

    private class AbilityFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Pokemon> filterList = new ArrayList<Pokemon>();
                for (int i = 0; i < fullList.size(); i++) {
                    if ( (fullList.get(i).getName().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {
                        Pokemon pokemon = new Pokemon();

                        pokemon.setId(fullList.get(i).getId());
                        pokemon.setRawName(fullList.get(i).getRawName());
                        pokemon.setSpeciesNumber(fullList.get(i).getSpeciesNumber());
                        pokemon.setHeight(fullList.get(i).getHeight());
                        pokemon.setWeight(fullList.get(i).getWeight());
                        pokemon.setBaseExperience(fullList.get(i).getBaseExperience());
                        pokemon.setOrder(fullList.get(i).getOrder());
                        pokemon.setTypeOne(fullList.get(i).getTypeOne());
                        pokemon.setTypeTwo(fullList.get(i).getTypeTwo());
                        for (int j = 0; j < fullList.get(i).getMyAbilities().size(); j++) {
                            pokemon.addAbility(fullList.get(i).getMyAbilities().get(j));
                        }

                        filterList.add(pokemon);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = fullList.size();
                results.values = fullList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            data = (ArrayList<Pokemon>)results.values;

            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new AbilityFilter();
        }
        return mFilter;
    }
}