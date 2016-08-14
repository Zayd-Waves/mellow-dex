/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          MainPokemonDetails                                  |
|   Description:    MainPokemonDetails fragment.                        |
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.utils.TypeUtils;
import pl.droidsonroids.gif.GifTextView;

public class MainPokemonDetails extends Fragment {

    private OnFragmentInteractionListener                           mListener;
    private Pokemon                                                 pokemon;
    private Context                                                 mContext;
    private GifTextView sprite;
    private boolean                                                 shiny;

    public MainPokemonDetails() {
        /* Required empty public constructor. */
    }

    public static MainPokemonDetails newInstance(Pokemon p, Context context) {
        MainPokemonDetails fragment = new MainPokemonDetails();
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

        /* Inflate the layout for this fragment. */
        View v = inflater.inflate(R.layout.fragment_main_pokemon_details, container, false);
        sprite = (GifTextView) v.findViewById(R.id.pokemonSprite);
        sprite.setBackgroundResource(pokemon.getSpriteId());
        /*
        sprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shiny) {
                    sprite.setBackgroundResource(pokemon.getSpriteId());
                } else {
                    sprite.setBackgroundResource(pokemon.getShinySpriteId());
                }
                shiny = !shiny;
            }
        });*/

        TextView hpCount = (TextView) v.findViewById(R.id.hpCount);
        TextView attackCount = (TextView) v.findViewById(R.id.attackCount);
        TextView defenseCount = (TextView) v.findViewById(R.id.defenseCount);
        TextView spAttackCount = (TextView) v.findViewById(R.id.spAttackCount);
        TextView spDefenseCount = (TextView) v.findViewById(R.id.spDefCount);
        TextView speedCount = (TextView) v.findViewById(R.id.speedCount);
        TextView totalCount = (TextView) v.findViewById(R.id.totalCount);

        ProgressBar hpGraph = (ProgressBar) v.findViewById(R.id.hp_progress_bar);
        ProgressBar attackGraph = (ProgressBar) v.findViewById(R.id.attack_progress_bar);
        ProgressBar defenseGraph = (ProgressBar) v.findViewById(R.id.defense_progress_bar);
        ProgressBar spAttackGraph = (ProgressBar) v.findViewById(R.id.spAttack_progress_bar);
        ProgressBar spDefenseGraph = (ProgressBar) v.findViewById(R.id.spDef_progress_bar);
        ProgressBar speedGraph = (ProgressBar) v.findViewById(R.id.speed_progress_bar);

        TextView text2 = (TextView) v.findViewById(R.id.pokemonName);
        TextView text3 = (TextView) v.findViewById(R.id.type_one);
        TextView text4 = (TextView) v.findViewById(R.id.type_two);

        text2.setText(pokemon.getName());

        if (pokemon.getTypeTwo() != null) {
            text3.setText(pokemon.getTypeOne().toUpperCase());
            text3.setBackgroundColor(TypeUtils.getTypeColour(pokemon.getTypeOne(), mContext));
            text4.setText(pokemon.getTypeTwo().toUpperCase());
            text4.setBackgroundColor(TypeUtils.getTypeColour(pokemon.getTypeTwo(), mContext));

        } else {
            text4.setText(pokemon.getTypeOne().toUpperCase());
            text4.setBackgroundColor(TypeUtils.getTypeColour(pokemon.getTypeOne(), mContext));
            text3.setVisibility(View.GONE);
        }
        shiny = false;

        hpCount.setText(pokemon.getBaseHP()+ "");
        attackCount.setText(pokemon.getBaseAttack() + "");
        defenseCount.setText(pokemon.getBaseDefense() + "");
        spAttackCount.setText(pokemon.getBaseSpecialAttack() + "");
        spDefenseCount.setText(pokemon.getBaseSpecialDefense() + "");
        speedCount.setText(pokemon.getBaseSpeed() + "");
        totalCount.setText(pokemon.getBaseTotal() + "");

        hpGraph.setProgress(pokemon.getBaseHP());
        attackGraph.setProgress(pokemon.getBaseAttack());
        defenseGraph.setProgress(pokemon.getBaseDefense());
        spAttackGraph.setProgress(pokemon.getBaseSpecialAttack());
        spDefenseGraph.setProgress(pokemon.getBaseSpecialDefense());
        speedGraph.setProgress(pokemon.getBaseSpeed());

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
    */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
