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

/**
 * Created by Zayd on 6/18/16.
 */
public class CaughtListAdapter extends ArrayAdapter<Pokemon> implements android.widget.Filterable {

    Context context;
    static int customLayout = R.layout.caught_list_row;
    int rowTextColour = 0;
    int rowColour = 0;
    List<Pokemon> fullList = null;
    List<Pokemon> data = null;
    private PokemonFilter mFilter = new PokemonFilter();

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
        PokemonHolder holder = null;
        if(row == null)
        {
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

    private int getTypeColour(String type) {
        int c = 0;

        if (type.toLowerCase().equals("normal")) {
            c = ContextCompat.getColor(context, R.color.normal);
        } else if (type.toLowerCase().equals("fire")) {
            c = ContextCompat.getColor(context, R.color.fire);
        } else if (type.toLowerCase().equals("fighting")) {
            c = ContextCompat.getColor(context, R.color.fighting);
        } else if (type.toLowerCase().equals("water")) {
            c = ContextCompat.getColor(context, R.color.water);
        } else if (type.toLowerCase().equals("flying")) {
            c = ContextCompat.getColor(context, R.color.flying);
        } else if (type.toLowerCase().equals("grass")) {
            c = ContextCompat.getColor(context, R.color.grass);
        } else if (type.toLowerCase().equals("poison")) {
            c = ContextCompat.getColor(context, R.color.poison);
        } else if (type.toLowerCase().equals("electric")) {
            c = ContextCompat.getColor(context, R.color.electric);
        } else if (type.toLowerCase().equals("ground")) {
            c = ContextCompat.getColor(context, R.color.ground);
        } else if (type.toLowerCase().equals("psychic")) {
            c = ContextCompat.getColor(context, R.color.psychic);
        } else if (type.toLowerCase().equals("rock")) {
            c = ContextCompat.getColor(context, R.color.rock);
        } else if (type.toLowerCase().equals("ice")) {
            c = ContextCompat.getColor(context, R.color.ice);
        } else if (type.toLowerCase().equals("bug")) {
            c = ContextCompat.getColor(context, R.color.bug);
        } else if (type.toLowerCase().equals("dragon")) {
            c = ContextCompat.getColor(context, R.color.dragon);
        } else if (type.toLowerCase().equals("ghost")) {
            c = ContextCompat.getColor(context, R.color.ghost);
        } else if (type.toLowerCase().equals("dark")) {
            c = ContextCompat.getColor(context, R.color.dark);
        } else if (type.toLowerCase().equals("steel")) {
            c = ContextCompat.getColor(context, R.color.steel);
        } else if (type.toLowerCase().equals("fairy")) {
            c = ContextCompat.getColor(context, R.color.fairy);
        }

        return c;
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