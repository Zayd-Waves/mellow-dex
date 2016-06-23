/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          Dashboard                                           |
|   Description:    Dashboard screen. Contains the various Dex's.       |
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

package me.zaydbille.pokedex.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.adapters.PagerAdapter;
import me.zaydbille.pokedex.data.Ability;
import me.zaydbille.pokedex.data.Move;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.database.DatabaseManager;
import me.zaydbille.pokedex.fragments.AbilityScreen;
import me.zaydbille.pokedex.fragments.CaughtScreen;
import me.zaydbille.pokedex.fragments.LocationScreen;
import me.zaydbille.pokedex.fragments.MoveScreen;
import me.zaydbille.pokedex.fragments.PokedexScreen;
import me.zaydbille.pokedex.fragments.TeamScreen;
import me.zaydbille.pokedex.fragments.TypeScreen;
import me.zaydbille.pokedex.storage.PreferencesManager;

public class Dashboard extends AppCompatActivity implements
        AbilityScreen.OnFragmentInteractionListener,
        CaughtScreen.OnFragmentInteractionListener,
        LocationScreen.OnFragmentInteractionListener,
        MoveScreen.OnFragmentInteractionListener,
        PokedexScreen.OnFragmentInteractionListener,
        TeamScreen.OnFragmentInteractionListener,
        TypeScreen.OnFragmentInteractionListener {

    private static final int RESULT_SETTINGS = 1;
    private Context mContext;

    private int caughtRowTextColour;
    private int rowColour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
            Refresh the theme after changing it in the settings.
        */
        mContext = this;
        String theme = PreferencesManager.getTheme(mContext);
        switch (theme) {
            case "LabTheme":
                setTheme(R.style.LabTheme);
                rowColour = R.color.textSecondary;
                caughtRowTextColour = R.color.textPrimary;
                break;

            case "DPTheme":
                setTheme(R.style.DPTheme);
                rowColour = R.color.textSecondary;
                caughtRowTextColour = R.color.textPrimaryDP;
                break;

            default:
                setTheme(R.style.LabTheme);
                rowColour = R.color.textSecondary;
                caughtRowTextColour = R.color.textPrimary;
                break;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        /*
            Retrieve all lists from the database and pass them over to the fragments.
        */
        DatabaseManager databaseAccess = DatabaseManager.getInstance(mContext);
        databaseAccess.open();
        List<Pokemon> pokemonList = databaseAccess.getPokemon();
        List<Move> moveList = databaseAccess.getMoves();
        List<Ability> abilityList = databaseAccess.getAbilities();
        databaseAccess.close();
        PreferencesManager.setAllPokemon(pokemonList);

        /*
            Set up the caught list. Only load default zero values if it's the first launch.
        */
        if(!PreferencesManager.getFirstTime(mContext).equals("nil")) {
            HashSet<String> caughtSet = new HashSet<>();
            PreferencesManager.saveAllCaughtPokemon(mContext, caughtSet);
            PreferencesManager.setFirstTIme(mContext);
        }


        /*
            Set the ViewPager's adapter (which initializes the fragments) and then
            set the default tab to the Pokedex.
        */
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter ( getSupportFragmentManager(),
                                                        6,
                                                        mContext,
                                                        pokemonList,
                                                        moveList,
                                                        abilityList,
                                                        caughtRowTextColour,
                                                        rowColour);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(2);


        /*
            Set up our custom ActionMenuView.
        */
        Toolbar t = (Toolbar) findViewById(R.id.tToolbar);
        ActionMenuView amvMenu = (ActionMenuView) t.findViewById(R.id.amvMenu);
        amvMenu.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onOptionsItemSelected(menuItem);
            }
        });
        setSupportActionBar(t);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivityForResult(intent, RESULT_SETTINGS);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_SETTINGS) {
            refreshActivity();
        }
    }
    public void onFragmentInteraction(Uri uri){
        /* Empty for now. */
    }

    /*
        The Caught Screen's onFragmentInteraction callback
     */
    public void onFragmentInteraction(int pokemonId, String n){
        Intent intent = new Intent(mContext, PokemonDetails.class);
        intent.putExtra("pokemonId", pokemonId);
        startActivity(intent);
    }

    /*
        The Pokedex Screen's onFragmentInteraction callback.
    */
    public void onFragmentInteraction(int pokemonId){
        Intent intent = new Intent(mContext, PokemonDetails.class);
        intent.putExtra("pokemonId", pokemonId);
        startActivity(intent);
    }

    private void refreshActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        finish();
        startActivity(intent);
    }
}