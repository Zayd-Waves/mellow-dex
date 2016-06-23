package me.zaydbille.pokedex.fragments.details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.adapters.ChoiceGridAdapter;
import me.zaydbille.pokedex.adapters.DetailsPagerAdapter;
import me.zaydbille.pokedex.adapters.TeamGridAdapter;
import me.zaydbille.pokedex.data.Pokemon;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SkillsDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SkillsDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillsDetails extends Fragment {

    private Pokemon pokemon;
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    private GridView gridView;
    private ChoiceGridAdapter choiceGridAdapter;

    public SkillsDetails() {
        // Required empty public constructor
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skills_details, container, false);
        gridView = (GridView) v.findViewById(R.id.gridView);
        TextView title = (TextView)v.findViewById(R.id.title);
        ListView moveList = (ListView)v.findViewById(R.id.moveList);


        title.setText("Level-Up");
        String[] choices = new String[]{"Level-Up", "TM/HM", "Egg Moves", "Move Tutor"};
        choiceGridAdapter = new ChoiceGridAdapter(mContext, choices);
        gridView.setAdapter(choiceGridAdapter);
        gridView.setNumColumns(4);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(mContext,((TextView) v.findViewById(R.id.choiceText)).getText(), Toast.LENGTH_SHORT).show();
            }
        });


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
