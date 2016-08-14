/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          Settings                                            |
|   Description:    Settings screen. Can change the app's theme here.   |
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
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.storage.PreferencesManager;

public class Settings extends AppCompatActivity {

    private static final int                            RESULT_SETTINGS = 1;
    private Context                                     mContext;
    private ActionMenuView                              amvMenu;
    private RadioGroup                                  radioGroup;
    private RadioButton                                 radioButtonLabTheme;
    private RadioButton                                 radioButtonDPTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        String currentTheme = PreferencesManager.getTheme(mContext);
        switch (currentTheme) {
            case "LabTheme":
                setTheme(R.style.LabTheme);
                break;

            case "DPTheme":
                setTheme(R.style.DPTheme);
                break;

            default:
                setTheme(R.style.LabTheme);
                break;
        }
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_settings);

        /* Set up our custom ActionMenuView. */
        Toolbar t = (Toolbar) findViewById(R.id.tToolbar);
        amvMenu = (ActionMenuView) t.findViewById(R.id.amvMenu);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Set up the RadioGroup. */
        radioGroup = (RadioGroup) findViewById(R.id.themeOptions);
        radioButtonLabTheme = (RadioButton)findViewById(R.id.radioButtonLabTheme);
        radioButtonDPTheme = (RadioButton)findViewById(R.id.radioButtonDPTheme);

        /* Set the current theme's button to 'selected'. */
        switch (currentTheme) {
            case "LabTheme":
                radioButtonLabTheme.setChecked(true);
                break;

            case "DPTheme":
                radioButtonDPTheme.setChecked(true);
                break;

            default:
                radioButtonLabTheme.setChecked(true);
                break;
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonLabTheme) {
                    changeTheme("LabTheme");
                } else if (checkedId == R.id.radioButtonDPTheme) {
                    changeTheme("DPTheme");
                }
            }
        });
    }

    private void changeTheme(String newTheme) {
        switch (newTheme) {
            case "LabTheme":
                PreferencesManager.setThemeToLabTheme(mContext);
                break;

            case "DPTheme":
                PreferencesManager.setThemeToDPTheme(mContext);
                break;

            default:
                PreferencesManager.setThemeToLabTheme(mContext);
                break;
        }

        Intent intent = new Intent();
        intent.putExtra("selected_theme", newTheme);
        setResult(RESULT_SETTINGS, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, amvMenu.getMenu());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }
}
