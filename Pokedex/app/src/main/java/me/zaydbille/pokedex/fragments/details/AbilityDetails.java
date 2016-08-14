/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          AbilityDetails                                      |
|   Description:    AbilityDetails fragment.                            |
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
package me.zaydbille.pokedex.fragments.details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.data.Ability;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.utils.AbilityUtils;

public class AbilityDetails extends Fragment {

    private OnFragmentInteractionListener   mListener;

    public AbilityDetails() {
        /* Required empty public constructor. */
    }

    private Context                             mContext;
    private Pokemon                             pokemon;

    public static AbilityDetails newInstance(Pokemon p, Context context) {
        AbilityDetails fragment = new AbilityDetails();
        fragment.pokemon = p;
        fragment.mContext = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ability, container, false);

        TextView ability1 = (TextView) v.findViewById(R.id.ability1);
        TextView abilityDescription1 = (TextView) v.findViewById(R.id.abilityDescription1);
        TextView ability2 = (TextView) v.findViewById(R.id.ability2);
        TextView abilityDescription2 = (TextView) v.findViewById(R.id.abilityDescription2);
        TextView ability3 = (TextView) v.findViewById(R.id.ability3);
        TextView abilityDescription3 = (TextView) v.findViewById(R.id.abilityDescription3);
        TextView ability4 = (TextView) v.findViewById(R.id.ability4);
        TextView abilityDescription4 = (TextView) v.findViewById(R.id.abilityDescription4);
        TextView ability5 = (TextView) v.findViewById(R.id.ability5);
        TextView abilityDescription5 = (TextView) v.findViewById(R.id.abilityDescription5);

        ability1.setVisibility(View.GONE);
        ability2.setVisibility(View.GONE);
        ability3.setVisibility(View.GONE);
        ability4.setVisibility(View.GONE);
        ability5.setVisibility(View.GONE);

        abilityDescription1.setVisibility(View.GONE);
        abilityDescription2.setVisibility(View.GONE);
        abilityDescription3.setVisibility(View.GONE);
        abilityDescription4.setVisibility(View.GONE);
        abilityDescription5.setVisibility(View.GONE);

        HashMap<Integer, TextView> titleMap = new HashMap<Integer, TextView>();
        HashMap<Integer, TextView> descriptionMap = new HashMap<Integer, TextView>();
        titleMap.put(0, ability1);
        titleMap.put(1, ability2);
        titleMap.put(2, ability3);
        titleMap.put(3, ability4);
        titleMap.put(4, ability5);

        descriptionMap.put(0, abilityDescription1);
        descriptionMap.put(1, abilityDescription2);
        descriptionMap.put(2, abilityDescription3);
        descriptionMap.put(3, abilityDescription4);
        descriptionMap.put(4, abilityDescription5);

        List<Ability> abilities = AbilityUtils.sortAbilitiesByHidden(pokemon.getMyAbilities());
        for (int i = 0; i < abilities.size(); i++) {
            if(i > 4) {
                break;
            }
            if (abilities.get(i).getType() == 0) {
                titleMap.get(i).setText(abilities.get(i).getName());
                descriptionMap.get(i).setText(abilities.get(i).getDescription());
                titleMap.get(i).setVisibility(View.VISIBLE);
                descriptionMap.get(i).setVisibility(View.VISIBLE);
            } else {
                titleMap.get(i).setText(abilities.get(i).getName() + " (Hidden)");
                descriptionMap.get(i).setText(abilities.get(i).getDescription());
                titleMap.get(i).setVisibility(View.VISIBLE);
                descriptionMap.get(i).setVisibility(View.VISIBLE);
            }
        }
        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
    */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
