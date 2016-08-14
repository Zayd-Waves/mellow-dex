/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          SkillsDetails                                       |
|   Description:    SkillsDetails fragment.                             |
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
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.adapters.ChoiceGridAdapter;
import me.zaydbille.pokedex.adapters.MoveListAdapter;
import me.zaydbille.pokedex.adapters.PokemonMoveListAdapter;
import me.zaydbille.pokedex.data.Move;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.utils.MoveUtils;
import me.zaydbille.pokedex.utils.PokedexUtils;

public class SkillsDetails extends Fragment {

    private Pokemon                                     pokemon;
    private Context                                     mContext;
    private OnFragmentInteractionListener               mListener;
    private GridView                                    gridView;
    private ChoiceGridAdapter                           choiceGridAdapter;
    private ListView                                    listView;
    private PokemonMoveListAdapter                      moveListAdapter;
    private TextView                                    currentSection;
    private List<Move>                                  moves;

    public SkillsDetails() {
        /* Required empty public constructor. */
    }

    public static SkillsDetails newInstance(Pokemon p, Context context) {
        SkillsDetails fragment = new SkillsDetails();
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
        View v = inflater.inflate(R.layout.fragment_skills_details, container, false);

        moves = (pokemon.getLevelUpMoves());
        listView = (ListView) v.findViewById(R.id.moveList);
        moveListAdapter = new PokemonMoveListAdapter(mContext, moves);
        listView.setAdapter(moveListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {

                Move m = (Move)listView.getItemAtPosition(position);
                // TODO: 6/04/16
                //onMovePressed(m);
            }
        });
        gridView = (GridView) v.findViewById(R.id.gridView);
        String[] choices = new String[]{"Level-Up", "TM/HM", "Egg Moves", "Move Tutor"};
        choiceGridAdapter = new ChoiceGridAdapter(mContext, choices);
        gridView.setAdapter(choiceGridAdapter);
        gridView.setNumColumns(4);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v,
                                    int position,
                                    long id) {
                if (!(currentSection == null)) {
                    currentSection.setTypeface(null, Typeface.NORMAL);
                    currentSection = ((TextView) v.findViewById(R.id.choiceText));
                } else {
                    currentSection = ((TextView) v.findViewById(R.id.choiceText));
                    choiceGridAdapter.clearTexts();
                }
                ((TextView) v.findViewById(R.id.choiceText)).setTypeface(null, Typeface.BOLD);
                String category = (String)((TextView) v.findViewById(R.id.choiceText)).getText();
                if (category.equals("Level-Up")) {
                    moves = pokemon.getLevelUpMoves();
                    moveListAdapter = new PokemonMoveListAdapter(mContext, moves);
                    listView.setAdapter(moveListAdapter);
                    moveListAdapter.notifyDataSetChanged();
                } else if (category.equals("TM/HM")) {
                    moves = MoveUtils.sortByNumber(pokemon.getMachineMoves());
                    moveListAdapter = new PokemonMoveListAdapter(mContext, moves);
                    listView.setAdapter(moveListAdapter);
                    moveListAdapter.notifyDataSetChanged();
                } else if (category.equals("Egg Moves")) {
                    moves = PokedexUtils.sortMovesByName(pokemon.getEggMoves());
                    moveListAdapter = new PokemonMoveListAdapter(mContext, moves);
                    listView.setAdapter(moveListAdapter);
                    moveListAdapter.notifyDataSetChanged();
                } else if (category.equals("Move Tutor")) {
                    moves = PokedexUtils.sortMovesByName(pokemon.getTutorMoves());
                    moveListAdapter = new PokemonMoveListAdapter(mContext, moves);
                    listView.setAdapter(moveListAdapter);
                    moveListAdapter.notifyDataSetChanged();
                }
            }
        });

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
