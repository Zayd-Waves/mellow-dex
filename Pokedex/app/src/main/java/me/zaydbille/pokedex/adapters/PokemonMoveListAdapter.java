/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          PokemonMoveListAdapter                              |
|   Description:    Custom list adapter for the MoveDetailsScreen's     |
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

public class PokemonMoveListAdapter extends ArrayAdapter<Move> implements android.widget.Filterable {

    private Context                             context;
    private static int                          customLayout = R.layout.pokemon_move_list_row;
    private List<Move>                          data = null;

    public PokemonMoveListAdapter(Context context, List<Move> data) {
        super(context, customLayout, data);
        this.context = context;
        this.data = data;
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
            holder.levelText = (TextView)row.findViewById(R.id.levelText);

            row.setTag(holder);
        } else {
            holder = (MoveHolder) row.getTag();
        }

        Move move = data.get(position);
        holder.moveName.setText(move.getName());
        holder.moveDescription.setText(move.getDescription());
        holder.moveType.setText(move.getType().toUpperCase());
        holder.moveType.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        if(move.getLearnType().equals("machine")) {
            holder.levelText.setText(move.getTmHmString());
        } else if(move.getLearnType().equals("level-up")) {
            String levelString = move.getLevel() + "";
            if (move.getLevel() == 0) {
                levelString = "---";
            }
            if (levelString.equals("00")) {
                levelString = "---";
            }
            holder.levelText.setText(levelString);
        } else {
            holder.levelText.setText("---");
        }

        /* Get the appropriate colour for the type. */
        int colourOne = TypeUtils.getTypeColour(move.getType(), context);
        holder.moveType.setBackgroundColor(colourOne);

        return row;
    }

    static class MoveHolder {
        TextView moveName;
        TextView moveDescription;
        TextView moveType;
        TextView levelText;
    }
}