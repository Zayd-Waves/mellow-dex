package me.zaydbille.pokedex.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.data.Team;
import me.zaydbille.pokedex.utils.TypeUtils;

public class TeamSpinnerAdapter extends ArrayAdapter<Team> {

    private Context                             context;
    private static int                          customLayout;
    private List<Team>                          data = null;

    public TeamSpinnerAdapter(Context con, int layout, List<Team> list) {
        super(con, layout, list);
        customLayout = layout;
        context = con;
        data = list;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Team getItem(int position) {
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TeamHolder holder;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(customLayout, parent, false);

            holder = new TeamHolder();
            holder.item = (TextView) row.findViewById(R.id.text1);

            row.setTag(holder);
        } else {
            holder = (TeamHolder) row.getTag();
        }

        Team team = data.get(position);
        holder.item.setText(team.getTitle());

        return row;
    }

    static class TeamHolder {
        TextView item;
    }
}
