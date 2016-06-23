package me.zaydbille.pokedex.adapters;

/**
 * Created by Zayd on 6/01/16.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import me.zaydbille.pokedex.data.Ability;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.fragments.AbilityScreen;
import me.zaydbille.pokedex.fragments.CaughtScreen;
import me.zaydbille.pokedex.fragments.LocationScreen;
import me.zaydbille.pokedex.fragments.MoveScreen;
import me.zaydbille.pokedex.fragments.PokedexScreen;
import me.zaydbille.pokedex.fragments.TeamScreen;
import me.zaydbille.pokedex.fragments.TypeScreen;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.fragments.AbilityScreen;
import me.zaydbille.pokedex.fragments.CaughtScreen;
import me.zaydbille.pokedex.fragments.LocationScreen;
import me.zaydbille.pokedex.fragments.MoveScreen;
import me.zaydbille.pokedex.fragments.PokedexScreen;
import me.zaydbille.pokedex.fragments.TeamScreen;
import me.zaydbille.pokedex.fragments.TypeScreen;
import me.zaydbille.pokedex.fragments.details.AbilityDetails;
import me.zaydbille.pokedex.fragments.details.EggDetails;
import me.zaydbille.pokedex.fragments.details.MainPokemonDetails;
import me.zaydbille.pokedex.fragments.details.PokemonTypeDetails;
import me.zaydbille.pokedex.fragments.details.SkillsDetails;

public class DetailsPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    Context context;
    Pokemon pokemon;

    public DetailsPagerAdapter(FragmentManager fm, int NumOfTabs, Context con, Pokemon p) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        context = con;
        pokemon = p;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PokemonTypeDetails tab1 = PokemonTypeDetails.newInstance(pokemon, context);
                return tab1;
            case 1:
                SkillsDetails tab2 = SkillsDetails.newInstance(pokemon, context);
                return tab2;
            case 2:
                MainPokemonDetails tab3 = MainPokemonDetails.newInstance(pokemon, context);
                return tab3;
            case 3:
                AbilityDetails tab4 = AbilityDetails.newInstance(pokemon, context);
                return tab4;
            case 4:
                EggDetails tab5 = new EggDetails();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Type";
                break;
            case 1:
                title = "Skills";
                break;
            case 2:
                title = "Main";
                break;
            case 3:
                title = "Ability";
                break;
            case 4:
                title = "Egg";
                break;
        }

        return title;
    }
}