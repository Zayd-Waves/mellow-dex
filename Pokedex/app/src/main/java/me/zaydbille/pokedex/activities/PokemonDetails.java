/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          PokemonDetails                                      |
|   Description:    Details of a single Pokemon.                        |
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
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.List;
import me.zaydbille.pokedex.R;
import me.zaydbille.pokedex.adapters.DetailsPagerAdapter;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.fragments.details.AbilityDetails;
import me.zaydbille.pokedex.fragments.details.EggDetails;
import me.zaydbille.pokedex.fragments.details.MainPokemonDetails;
import me.zaydbille.pokedex.fragments.details.PokemonTypeDetails;
import me.zaydbille.pokedex.fragments.details.SkillsDetails;
import me.zaydbille.pokedex.storage.PreferencesManager;

public class PokemonDetails extends AppCompatActivity implements
        MainPokemonDetails.OnFragmentInteractionListener,
        SkillsDetails.OnFragmentInteractionListener,
        PokemonTypeDetails.OnFragmentInteractionListener,
        AbilityDetails.OnFragmentInteractionListener,
        EggDetails.OnFragmentInteractionListener {

    private ActionMenuView                      amvMenu;
    private Pokemon                             pokemon;
    private Context                             mContext;
    private int                                 tabColour;
    private List<Pokemon>                       allPokemon;
    private TextView                            toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        String theme = PreferencesManager.getTheme(mContext);
        switch (theme) {
            case "LabTheme":
                setTheme(R.style.LabTheme);
                tabColour = R.color.colorPrimary;
                break;

            case "DPTheme":
                setTheme(R.style.DPTheme);
                tabColour = R.color.colorPrimaryDP;
                break;

            default:
                setTheme(R.style.LabTheme);
                tabColour = R.color.colorPrimary;
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        /* This screen is based off of this Pokemon instance */
        Bundle bundle = getIntent().getExtras();
        int pokemonId = bundle.getInt("pokemonId");

        allPokemon = PreferencesManager.getAllPokemon();
        for (int i = 0; i < allPokemon.size(); i++) {
            if (allPokemon.get(i).getId() == pokemonId) {
                pokemon = allPokemon.get(i);
            }
        }

        /*
            Set the ViewPager's adapter (which initializes the fragments) and then
            set the default tab to the Pokedex.
        */
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        //final DetailsPagerAdapter adapter = new DetailsPagerAdapter
        //        (getSupportFragmentManager(), 5, mContext, pokemon);
        final DetailsPagerAdapter adapter = new DetailsPagerAdapter
                (getSupportFragmentManager(), 5, mContext, pokemon);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(2);

        /* Set up our custom ActionMenuView. */
        Toolbar t = (Toolbar) findViewById(R.id.tToolbar);
        toolbarTitle = (TextView)t.findViewById(R.id.detailsTitle);
        toolbarTitle.setText(pokemon.getNumberString().substring(1));
        amvMenu = (ActionMenuView) t.findViewById(R.id.amvMenu);
        amvMenu.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onOptionsItemSelected(menuItem);
            }
        });
        t.showOverflowMenu();
        t.inflateMenu(R.menu.menu_pokemon_details);
        t.showOverflowMenu();

        setSupportActionBar(t);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(ContextCompat.getColor(mContext, tabColour));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pokemon_details, amvMenu.getMenu());
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public void onFragmentInteraction(Uri uri){
        /* Empty for now. */
    }
}