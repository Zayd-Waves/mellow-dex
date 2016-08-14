/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          TypeScreen                                          |
|   Description:    TypeScreen fragment.                                |
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.utils.TypeUtils;

public class TypeScreen extends Fragment {

    private OnFragmentInteractionListener           mListener;
    private List<String>                            types;
    private Context                                 mContext;

    public TypeScreen() {
        /* Required empty public constructor. */
    }

    public static TypeScreen newInstance(String param1, String param2) {
        TypeScreen fragment = new TypeScreen();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        types = new ArrayList<>();
        types.add("---");
        types.add("Fire");
        types.add("Water");
        types.add("Grass");
        types.add("Rock");
        types.add("Steel");
        types.add("Ground" );
        types.add("Electric");
        types.add("Flying");
        types.add("Fighting");
        types.add("Psychic");
        types.add("Dark");
        types.add("Dragon");
        types.add("Fairy");
        types.add("Ice");
        types.add("Normal");
        types.add("Ghost");
        types.add("Poison");
        types.add("Bug");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             Bundle savedInstanceState) {

        /* Inflate the layout for this fragment. */
        View v =  inflater.inflate(R.layout.fragment_typescreen, container, false);
        mContext = getContext();

        /* The Linear Layouts. */
        final LinearLayout strongAgainst = (LinearLayout) v.findViewById(R.id.strongAgainstLayout);
        final LinearLayout weakAgainst = (LinearLayout) v.findViewById(R.id.weakAgainstLayout);
        final LinearLayout noEffectAgainst = (LinearLayout) v.findViewById(R.id.noEffectAgainst);
        final LinearLayout immuneTo = (LinearLayout) v.findViewById(R.id.immuneToLayout);
        final LinearLayout resists = (LinearLayout) v.findViewById(R.id.resistsLayout);
        final LinearLayout weakTo = (LinearLayout) v.findViewById(R.id.weakToLayout);

        final Spinner typeOneSpinner = (Spinner) v.findViewById(R.id.typeOneSpinner);
        final Spinner typeTwoSpinner = (Spinner) v.findViewById(R.id.typeTwoSpinner);

        typeOneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (strongAgainst.getChildCount() > 0) {
                    strongAgainst.removeAllViews();
                }
                if (weakAgainst.getChildCount() > 0) {
                    weakAgainst.removeAllViews();
                }
                if (noEffectAgainst.getChildCount() > 0) {
                    noEffectAgainst.removeAllViews();
                }
                if (immuneTo.getChildCount() > 0) {
                    immuneTo.removeAllViews();
                }
                if (resists.getChildCount() > 0) {
                    resists.removeAllViews();
                }
                if (weakTo.getChildCount() > 0) {
                    weakTo.removeAllViews();
                }

                String item = parent.getItemAtPosition(position).toString();
                String item2 = typeTwoSpinner.getSelectedItem().toString();

                HashMap<String, Double> offenses = TypeUtils.getCoverageOffenseDual(item, item2);
                HashMap<String, Double> defenses = TypeUtils.getCoverageDefenseDual(item, item2);

                for (Map.Entry<String, Double> entry : offenses.entrySet()) {
                    String key = entry.getKey();
                    Double value = entry.getValue();

                    String text = key.toUpperCase() + " " + value;
                    LinearLayout typeBadge = (LinearLayout) inflater.inflate(R.layout.type_badge_basic, container, false);
                    TextView type = (TextView)typeBadge.findViewById(R.id.type);
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

                if (!(item.equals("---")) && !(item2.equals("---"))) {
                    strongAgainst.removeAllViews();
                    weakAgainst.removeAllViews();
                    noEffectAgainst.removeAllViews();
                }

                for (Map.Entry<String, Double> entry : defenses.entrySet()) {
                    String key = entry.getKey();
                    Double value = entry.getValue();

                    String text = key.toUpperCase() + " " + value;
                    LinearLayout typeBadge = (LinearLayout) inflater.inflate(R.layout.type_badge_basic, container, false);
                    TextView type = (TextView)typeBadge.findViewById(R.id.type);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        typeTwoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (strongAgainst.getChildCount() > 0) {
                    strongAgainst.removeAllViews();
                }
                if (weakAgainst.getChildCount() > 0) {
                    weakAgainst.removeAllViews();
                }
                if (noEffectAgainst.getChildCount() > 0) {
                    noEffectAgainst.removeAllViews();
                }
                if (immuneTo.getChildCount() > 0) {
                    immuneTo.removeAllViews();
                }
                if (resists.getChildCount() > 0) {
                    resists.removeAllViews();
                }
                if (weakTo.getChildCount() > 0) {
                    weakTo.removeAllViews();
                }
                String item2 = parent.getItemAtPosition(position).toString();
                String item = typeOneSpinner.getSelectedItem().toString();

                HashMap<String, Double> offenses = TypeUtils.getCoverageOffenseDual(item, item2);
                HashMap<String, Double> defenses = TypeUtils.getCoverageDefenseDual(item, item2);

                for (Map.Entry<String, Double> entry : offenses.entrySet()) {
                    String key = entry.getKey();
                    Double value = entry.getValue();

                    String text = key.toUpperCase() + " " + value;
                    LinearLayout typeBadge = (LinearLayout) inflater.inflate(R.layout.type_badge_basic, container, false);
                    TextView type = (TextView)typeBadge.findViewById(R.id.type);
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

                if (!(item.equals("---")) && !(item2.equals("---"))) {
                    strongAgainst.removeAllViews();
                    weakAgainst.removeAllViews();
                    noEffectAgainst.removeAllViews();
                }

                for (Map.Entry<String, Double> entry : defenses.entrySet()) {
                    String key = entry.getKey();
                    Double value = entry.getValue();

                    String text = key.toUpperCase() + " " + value;
                    LinearLayout typeBadge = (LinearLayout) inflater.inflate(R.layout.type_badge_basic, container, false);
                    TextView type = (TextView)typeBadge.findViewById(R.id.type);
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /* Creating adapter for spinner. */
        ArrayAdapter dataAdapterOne = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, types);
        ArrayAdapter dataAdapterTwo = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, types);

        dataAdapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeOneSpinner.setAdapter(dataAdapterOne);
        typeTwoSpinner.setAdapter(dataAdapterTwo);

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
