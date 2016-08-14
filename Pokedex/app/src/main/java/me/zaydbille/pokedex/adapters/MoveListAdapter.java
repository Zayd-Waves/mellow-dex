/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          MoveListAdapter                                     |
|   Description:    Custom list adapter for the MoveScreen's            |
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
import me.zaydbille.pokedex.data.Move;
import me.zaydbille.pokedex.utils.TypeUtils;

public class MoveListAdapter extends ArrayAdapter<Move> implements android.widget.Filterable {

    private Context                             context;
    private static int                          customLayout = R.layout.move_list_row;
    private List<Move>                          data = null;
    private List<Move>                          fullList = null;
    private MoveFilter                          mFilter = new MoveFilter();

    public MoveListAdapter(Context context, List<Move> data) {
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
        MoveHolder holder;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(customLayout, parent, false);

            holder = new MoveHolder();
            holder.moveName = (TextView) row.findViewById(R.id.moveName);
            holder.moveDescription = (TextView)row.findViewById(R.id.moveDescription);
            holder.moveType = (TextView)row.findViewById(R.id.type);

            row.setTag(holder);
        } else {
            holder = (MoveHolder) row.getTag();
        }

        Move move = data.get(position);
        holder.moveName.setText(move.getName());
        holder.moveDescription.setText(move.getDescription());
        holder.moveType.setText(move.getType().toUpperCase());
        holder.moveType.setTextColor(ContextCompat.getColor(context, android.R.color.white));

        /* Get the appropriate colour for the type. */
        int colourOne = TypeUtils.getTypeColour(move.getType(), context);
        holder.moveType.setBackgroundColor(colourOne);

        return row;
    }

    static class MoveHolder {
        TextView moveName;
        TextView moveDescription;
        TextView moveType;
    }

    private class MoveFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<Move> filterList = new ArrayList<Move>();
                for (int i = 0; i < fullList.size(); i++) {
                    if ( (fullList.get(i).getName().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {
                        Move move = new Move();

                        move.setRawName(fullList.get(i).getRawName());
                        move.setPower(fullList.get(i).getPower());
                        move.setDescription(fullList.get(i).getDescription());
                        move.setEffectChance(fullList.get(i).getEffectChance());
                        move.setPp(fullList.get(i).getPp());
                        move.setAccuracy(fullList.get(i).getAccuracy());
                        move.setCategory(fullList.get(i).getCategory());
                        move.setId(fullList.get(i).getId());
                        move.setType(fullList.get(i).getType());

                        filterList.add(move);
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

            data = (ArrayList<Move>)results.values;

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
            mFilter = new MoveFilter();
        }
        return mFilter;
    }
}