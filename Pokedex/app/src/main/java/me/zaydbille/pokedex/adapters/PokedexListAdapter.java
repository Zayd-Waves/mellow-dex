/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          PokedexListAdapter                                  |
|   Description:    Custom list adapter for the PokedexScreen's         |
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
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.utils.TypeUtils;

public class PokedexListAdapter extends ArrayAdapter<Pokemon> implements android.widget.Filterable {

    private Context                             context;
    private static int                          customLayout = R.layout.pokedex_list_row;
    private List<Pokemon>                       fullList = null;
    private List<Pokemon>                       data = null;
    private PokemonFilter                       mFilter = new PokemonFilter();

    public PokedexListAdapter(Context context, List<Pokemon> data) {
        super(context, customLayout, data);
        this.context = context;
        this.data = data;
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
            holder.typeOne = (TextView)row.findViewById(R.id.type_one);
            holder.typeTwo = (TextView)row.findViewById(R.id.type_two);

            row.setTag(holder);
        } else {
            holder = (PokemonHolder)row.getTag();
        }

        Pokemon pokemon = data.get(position);
        holder.pokedexNumber.setText(pokemon.getNumberString());
        holder.pokemonName.setText(pokemon.getName());

        if (pokemon.getTypeOne() ==  null) {
            holder.typeOne.setVisibility(View.INVISIBLE);
        } else {
            holder.typeOne.setVisibility(View.VISIBLE);
            holder.typeOne.setText(pokemon.getTypeOne().toUpperCase());
            holder.typeOne.setTextColor(ContextCompat.getColor(context, android.R.color.white));

            /* Get the appropriate colour for the type. */
            int colourTwo = TypeUtils.getTypeColour(pokemon.getTypeOne(), context);
            holder.typeOne.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
            holder.typeOne.setBackgroundColor(colourTwo);
        }

        if (pokemon.getTypeTwo() ==  null) {
            holder.typeTwo.setVisibility(View.INVISIBLE);
        } else {
            holder.typeTwo.setVisibility(View.VISIBLE);
            holder.typeTwo.setText(pokemon.getTypeTwo().toUpperCase());
            holder.typeTwo.setTextColor(ContextCompat.getColor(context, android.R.color.white));

            /* Get the appropriate colour for the type. */
            int colourTwo = TypeUtils.getTypeColour(pokemon.getTypeTwo(), context);
            holder.typeTwo.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
            holder.typeTwo.setBackgroundColor(colourTwo);
        }

        return row;
    }

    static class PokemonHolder {
        TextView pokedexNumber;
        TextView pokemonName;
        TextView typeOne;
        TextView typeTwo;
    }

    private class PokemonFilter extends Filter {
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
            mFilter = new PokemonFilter();
        }
        return mFilter;
    }
}