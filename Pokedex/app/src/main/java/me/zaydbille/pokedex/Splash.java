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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.zaydbille.pokedex.activities.Dashboard;
import me.zaydbille.pokedex.storage.PreferencesManager;

public class Splash extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
            We're going to set the app's theme here.
        */
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
    }

    private void startApp() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}
