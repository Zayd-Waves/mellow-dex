package me.zaydbille.pokedex.fragments.details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.utils.TypeUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PokemonTypeDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PokemonTypeDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonTypeDetails extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PokemonTypeDetails() {
        // Required empty public constructor
    }


    Context mContext;
    Pokemon pokemon;

    // TODO: Rename and change types and number of parameters
    public static PokemonTypeDetails newInstance(Pokemon p, Context context) {
        PokemonTypeDetails fragment = new PokemonTypeDetails();
        fragment.mContext = context;
        fragment.pokemon = p;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pokemon_type_details, container, false);


        TextView type_one = (TextView)v.findViewById(R.id.type_one);
        TextView type_two = (TextView)v.findViewById(R.id.type_two);
        if (pokemon.getTypeTwo() != null) {
            type_one.setText(pokemon.getTypeOne().toUpperCase());
            type_one.setBackgroundColor(TypeUtils.getTypeColour(pokemon.getTypeOne(), mContext));
            type_two.setText(pokemon.getTypeTwo().toUpperCase());
            type_two.setBackgroundColor(TypeUtils.getTypeColour(pokemon.getTypeTwo(), mContext));

        } else {
            type_two.setText(pokemon.getTypeOne().toUpperCase());
            type_two.setBackgroundColor(TypeUtils.getTypeColour(pokemon.getTypeOne(), mContext));
            type_one.setVisibility(View.GONE);
        }

        // The Linear Layouts
        final LinearLayout strongAgainst = (LinearLayout) v.findViewById(R.id.strongAgainstLayout);
        final LinearLayout weakAgainst = (LinearLayout) v.findViewById(R.id.weakAgainstLayout);
        final LinearLayout noEffectAgainst = (LinearLayout) v.findViewById(R.id.noEffectAgainst);
        final LinearLayout immuneTo = (LinearLayout) v.findViewById(R.id.immuneToLayout);
        final LinearLayout resists = (LinearLayout) v.findViewById(R.id.resistsLayout);
        final LinearLayout weakTo = (LinearLayout) v.findViewById(R.id.weakToLayout);

        final Spinner typeOneSpinner = (Spinner) v.findViewById(R.id.typeOneSpinner);
        final Spinner typeTwoSpinner = (Spinner) v.findViewById(R.id.typeTwoSpinner);

        String type1 = "---";
        String type2 = "---";
        if (pokemon.getTypeTwo() == null || pokemon.getTypeTwo().equals("")) {
            type1 = pokemon.getTypeOne();
        } else {
            type1 = pokemon.getTypeOne();
            type2 = pokemon.getTypeTwo();
        }

        HashMap<String, Double> offenses = TypeUtils.getCoverageOffenseDual(type1, type2);
        HashMap<String, Double> defenses = TypeUtils.getCoverageDefenseDual(type1, type2);

        for (Map.Entry<String, Double> entry : offenses.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            String text = key.toUpperCase() + " " + value;
            LinearLayout typeBadge = (LinearLayout) inflater.inflate(R.layout.type_badge_basic, container, false);
            TextView type = (TextView) typeBadge.findViewById(R.id.type);
            type.setText(text);
            type.setBackgroundColor(TypeUtils.getTypeColour(key, mContext));
            type.setGravity(Gravity.CENTER);
            type.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

            if (value > 1.0) {
                strongAgainst.addView(typeBadge);
            } else if (value == 0.0) {
                noEffectAgainst.addView(typeBadge);
            } else if (value < 1.0) {
                weakAgainst.addView(typeBadge);
            }
        }

        for (Map.Entry<String, Double> entry : defenses.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            String text = key.toUpperCase() + " " + value;
            LinearLayout typeBadge = (LinearLayout) inflater.inflate(R.layout.type_badge_basic, container, false);
            TextView type = (TextView) typeBadge.findViewById(R.id.type);
            type.setText(text);
            type.setBackgroundColor(TypeUtils.getTypeColour(key, mContext));
            type.setGravity(Gravity.CENTER);
            type.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

            if (value > 1.0) {
                weakTo.addView(typeBadge);
            } else if (value == 0.0) {
                immuneTo.addView(typeBadge);
            } else if (value < 1.0) {
                resists.addView(typeBadge);
            }
        }


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
