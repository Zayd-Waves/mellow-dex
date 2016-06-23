package me.zaydbille.pokedex.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.adapters.TeamGridAdapter;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.data.Team;
import me.zaydbille.pokedex.storage.PreferencesManager;
import me.zaydbille.pokedex.utils.TypeUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamScreen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private GridView gridView;
    private Context mContext;
    private List<Team> teams;
    private Team currentTeam;
    private TeamGridAdapter teamGridAdapter;
    private String newTeamName;

    public TeamScreen() {
        // Required empty public constructor
    }
    public static TeamScreen newInstance(Context context) {
        TeamScreen fragment = new TeamScreen();
        fragment.mContext = context;
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
        View v = inflater.inflate(R.layout.fragment_teamscreen, container, false);

        ImageView optionsButton = (ImageView)v.findViewById(R.id.optionsButton);
        gridView = (GridView) v.findViewById(R.id.gridView);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsMenu();
            }
        });

        Spinner teamSpinner = (Spinner) v.findViewById(R.id.teamSpinner);
        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Creating adapter for spinner
        teams = PreferencesManager.getTeams(mContext);
        String[] teamTitles = new String[teams.size()];
        if (teams.size() == 0) {
            // show empty view
            gridView.setVisibility(View.INVISIBLE);
        } else {
            gridView.setVisibility(View.VISIBLE);
            for(int i = 0; i < teams.size(); i++) {
                teamTitles[i] = teams.get(i).getTitle();
            }
        }
        ArrayAdapter dataAdapterOne = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, teamTitles);
        dataAdapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSpinner.setAdapter(dataAdapterOne);

        if (!(teams.size() == 0)) {
            currentTeam = teams.get(0);
            currentTeam.setPokemon(teams.get(0).getPokemon());
        } else {
            currentTeam = new Team();
            currentTeam.setPokemon(new Pokemon[]{   new Pokemon(65, "Charmander", "fire", null),
                    new Pokemon(66, "Entei", "fire", null),
                    new Pokemon(67, "Ho-Oh", "fire", "fire"),
                    new Pokemon(68, "Vulpix", "fire", null),
                    new Pokemon(69, "Flareon", "fire", null),
                    new Pokemon(70, "Fennekin", "fire", null)});
        }
        teamGridAdapter = new TeamGridAdapter(mContext, currentTeam.getPokemon());
        gridView.setAdapter(teamGridAdapter);
        gridView.setNumColumns(2);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(mContext,((TextView) v.findViewById(R.id.pokemonText)).getText(), Toast.LENGTH_SHORT).show();
            }
        });

        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String teamTitle = parent.getItemAtPosition(position).toString();
                Pokemon[] team = new Pokemon[6];
                for(int i = 0; i < teams.size(); i++) {
                    if(teams.get(i).getTitle().equals(teamTitle)) {
                        team = teams.get(i).getPokemon();
                    }
                }
                currentTeam.setPokemon(team);
                teamGridAdapter.notifyDataSetChanged();
                teamGridAdapter.notifyDataSetChanged();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return v;
    }

    public void openOptionsMenu() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(mContext);
        builderSingle.setIcon(R.mipmap.ic_launcher);
        builderSingle.setTitle("");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                mContext,
                R.layout.dialog_list_layout,
                R.id.text1);
        arrayAdapter.add("Create Team");
        arrayAdapter.add("Rename Team");
        arrayAdapter.add("Delete Team");

        builderSingle.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String choice = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(mContext);
                        if (choice.equals("Create Team")) {
                            builderInner.setTitle("Create Team");

                            // Set up the input
                            final EditText input = new EditText(mContext);
                            input.setInputType(InputType.TYPE_CLASS_TEXT);
                            builderInner.setView(input);

                            // Set up the buttons
                            builderInner.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    newTeamName = input.getText().toString();
                                    dialog.dismiss();
                                }
                            });
                            builderInner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            builderInner.show();

                        } else if (choice.equals("Rename Team")) {
                            dialog.dismiss();

                        } else if (choice.equals("Delete Team")) {
                            dialog.dismiss();
                        }
                    }
                });
        builderSingle.show();
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
