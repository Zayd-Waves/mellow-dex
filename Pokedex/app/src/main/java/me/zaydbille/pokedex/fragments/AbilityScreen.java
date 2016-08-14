/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          AbilityScreen                                       |
|   Description:    AbilityScreen fragment.                             |
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
import android.net.Uri;
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
import me.zaydbille.pokedex.adapters.AbilityListAdapter;
import me.zaydbille.pokedex.data.Ability;
import me.zaydbille.pokedex.utils.PokedexUtils;

public class AbilityScreen extends Fragment {

    private OnFragmentInteractionListener                           mListener;
    private Context                                                 mContext;
    private ListView                                                listView;
    private AbilityListAdapter                                      abilityListAdapter;
    private List<Ability>                                           abilityList;

    public AbilityScreen() {
        /* Required empty public constructor. */
    }

    public static AbilityScreen newInstance(Context con, List<Ability> abilities) {
        AbilityScreen fragment = new AbilityScreen();
        fragment.mContext = con;
        fragment.abilityList = abilities;
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
        View v = inflater.inflate(R.layout.fragment_abilityscreen, container, false);

        EditText searchBox = (EditText)v.findViewById(R.id.searchBox);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs,
                                      int arg1,
                                      int arg2,
                                      int arg3) {

                /* When the user changes the Text. */
                abilityListAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0,
                                          int arg1,
                                          int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        listView = (ListView) v.findViewById(R.id.abilityList);
        abilityListAdapter = new AbilityListAdapter(mContext, PokedexUtils.sortAbilitiesByName(abilityList));
        listView.setAdapter(abilityListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {

                Ability a = (Ability) listView.getItemAtPosition(position);

                // TODO: 6/04/16
                //onMovePressed(m);
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
