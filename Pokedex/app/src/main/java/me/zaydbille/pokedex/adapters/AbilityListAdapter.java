/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          AbilityListAdapter                                  |
|   Description:    Custom list adapter for the AbilityScreen's         |
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.data.Ability;

public class AbilityListAdapter extends ArrayAdapter<Ability> implements android.widget.Filterable {

    private Context                             context;
    static int                                  customLayout = R.layout.ability_list_row;
    private List<Ability>                       data = null;
    private List<Ability>                       fullList = null;
    private AbilityFilter                       mFilter = new AbilityFilter();

    public AbilityListAdapter(Context context, List<Ability> data) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AbilityHolder holder;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(customLayout, parent, false);

            holder = new AbilityHolder();
            holder.abilityName = (TextView) row.findViewById(R.id.abilityName);
            holder.abilityDescription = (TextView)row.findViewById(R.id.abilityDescription);

            row.setTag(holder);
        } else {
            holder = (AbilityHolder) row.getTag();
        }

        Ability ability = data.get(position);
        holder.abilityName.setText(ability.getName());
        holder.abilityDescription.setText(ability.getDescription());

        return row;
    }

    static class AbilityHolder {
        TextView abilityName;
        TextView abilityDescription;
    }

    private class AbilityFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<Ability> filterList = new ArrayList<Ability>();
                for (int i = 0; i < fullList.size(); i++) {
                    if ( (fullList.get(i).getName().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {
                        Ability ability = new Ability();

                        ability.setName(fullList.get(i).getRawName());
                        ability.setDescription(fullList.get(i).getDescription());
                        ability.setAbilityId(fullList.get(i).getAbilityId());
                        ability.setType(fullList.get(i).getType());

                        filterList.add(ability);
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
            data = (ArrayList<Ability>)results.values;

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