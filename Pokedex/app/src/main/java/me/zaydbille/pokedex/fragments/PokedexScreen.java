/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          PokedexScreen                                       |
|   Description:    PokedexScreen fragment.                             |
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
package me.zaydbille.pokedex.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.adapters.PokedexListAdapter;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.utils.PokedexUtils;

public class PokedexScreen extends Fragment {

    private OnFragmentInteractionListener                       mListener;
    private ListView                                            listView;
    private static List<Pokemon>                                pokemonList;
    private PokedexListAdapter                                  adapter;
    private static Context                                      context;

    public PokedexScreen() {
        /* Required empty public constructor. */
    }

    public static PokedexScreen newInstance(Context cont, List<Pokemon> data) {
        PokedexScreen fragment = new PokedexScreen();
        context = cont;
        pokemonList = data;
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
        View view = inflater.inflate(R.layout.fragment_pokedexscreen, null);
        EditText searchBox = (EditText)view.findViewById(R.id.searchBox);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs,
                                      int arg1,
                                      int arg2,
                                      int arg3) {
                /* When the user changes the text. */
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0,
                                          int arg1,
                                          int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        listView = (ListView) view.findViewById(R.id.pokedexList);
        adapter = new PokedexListAdapter(context, PokedexUtils.sortByNumber(pokemonList));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Pokemon p = (Pokemon)parent.getAdapter().getItem(position);
                onPokedexEntryPressed(p.getId(), position);
            }
        });
        return view;
    }

    public void onPokedexEntryPressed(int pokemonId, int position) {
        if (mListener != null) {
            mListener.onFragmentInteraction(pokemonId, position);
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
        void onFragmentInteraction(int pokemonId, int position);
    }
}
