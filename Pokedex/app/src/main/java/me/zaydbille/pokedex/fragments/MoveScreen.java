/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          MoveScreen                                          |
|   Description:    MoveScreen fragment.                                |
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
import me.zaydbille.pokedex.adapters.MoveListAdapter;
import me.zaydbille.pokedex.data.Move;
import me.zaydbille.pokedex.utils.PokedexUtils;

public class MoveScreen extends Fragment {

    private OnFragmentInteractionListener                   mListener;
    private static List<Move>                               moveList;
    private Context                                         mContext;
    private ListView                                        listView;
    private MoveListAdapter                                 moveListAdapter;

    public MoveScreen() {
        /* Required empty public constructor. */
    }

    public static MoveScreen newInstance(Context con, List<Move> moves) {
        MoveScreen fragment = new MoveScreen();
        fragment.mContext = con;
        fragment.moveList = moves;
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
        View v = inflater.inflate(R.layout.fragment_movescreen, container, false);

        EditText searchBox = (EditText)v.findViewById(R.id.searchBox);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                /* When the user changes the text. */
                moveListAdapter.getFilter().filter(cs);
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

        listView = (ListView) v.findViewById(R.id.moveList);
        moveListAdapter = new MoveListAdapter(mContext, PokedexUtils.sortMovesByName(moveList));
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
