/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          Splash                                              |
|   Description:    Splash screen. Displays logo while app loads.       |
|                   If it's the first time the app is being run,        |
|                   we'll start the new user setup                      |
|                                                                       |
|   Author:         Zayd-Waves                                          |
|   Date:           5/31/2016                                           |
|                                                                       |
|                                                                       |
|                                                                       |
 -----------------------------------------------------------------------
*/
package me.zaydbille.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;

import me.zaydbille.pokedex.activities.Dashboard;
import me.zaydbille.pokedex.data.Ability;
import me.zaydbille.pokedex.data.Move;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.database.DatabaseManager;
import me.zaydbille.pokedex.storage.PreferencesManager;

public class Splash extends AppCompatActivity {

    private Context             mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* We're going to set the app's theme here. */
        mContext = this;
        String theme = PreferencesManager.getTheme(mContext);
        switch (theme) {
            case "LabTheme":
                setTheme(R.style.LabTheme);
                super.onCreate(savedInstanceState);
                startApp();
                break;

            case "DPTheme":
                setTheme(R.style.DPTheme);
                super.onCreate(savedInstanceState);
                startApp();
                break;

            default:
                setTheme(R.style.LabTheme);
                super.onCreate(savedInstanceState);
                startApp();
                break;
        }

        /* Retrieve all lists from the database and save them to our static class. */
        DatabaseManager databaseAccess = DatabaseManager.getInstance(mContext);
        databaseAccess.open();
        List<Pokemon> pokemonList = databaseAccess.getPokemon();
        List<Move> moveList = databaseAccess.getMoves();
        List<Ability> abilityList = databaseAccess.getAbilities();
        databaseAccess.close();
        PreferencesManager.setAllPokemon(pokemonList);
        PreferencesManager.setAllMoves(moveList);
        PreferencesManager.setAllAbilities(abilityList);


        /* Set up the caught list. Only load default zero values if it's the first launch. */
        if(!PreferencesManager.getFirstTime(mContext).equals("nil")) {
            HashSet<String> caughtSet = new HashSet<>();
            PreferencesManager.saveAllCaughtPokemon(mContext, caughtSet);
            PreferencesManager.setFirstTIme(mContext);
        }


        new LoadPokemonDataAsyncTask().execute();
    }

    private void startApp() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    private class LoadPokemonDataAsyncTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            DatabaseManager dbManager = DatabaseManager.getInstance(mContext);
            dbManager.open();
            List<Pokemon> pokeList = dbManager.getAllPokemonData();
            PreferencesManager.setAllPokemon(pokeList);
            dbManager.close();
            Log.d("TAG", "Loading Complete");
            return null;
        }
    }
}
