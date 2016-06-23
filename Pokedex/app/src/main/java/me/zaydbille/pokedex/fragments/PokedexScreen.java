package me.zaydbille.pokedex.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.adapters.PokedexListAdapter;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.database.DatabaseManager;
import me.zaydbille.pokedex.utils.PokedexUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PokedexScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PokedexScreen#newInstance} factory method to
 * create an instance of this fragment.
 */

public class PokedexScreen extends Fragment {

    ListView listView;
    static List<Pokemon> pokemonList;
    PokedexListAdapter adapter;
    static Context context;

    private OnFragmentInteractionListener mListener;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokedexscreen, null);

        EditText searchBox = (EditText)view.findViewById(R.id.searchBox);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        listView = (ListView) view.findViewById(R.id.pokedexList);
        adapter = new PokedexListAdapter(context, PokedexUtils.sortByNumber(pokemonList));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pokemon p = (Pokemon)parent.getAdapter().getItem(position);
                onPokedexEntryPressed(p.getId());

            }
        });



        return view;
    }

    public void onPokedexEntryPressed(int pokemonId) {
        if (mListener != null) {
            mListener.onFragmentInteraction(pokemonId);
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int pokemonId);
    }
}
