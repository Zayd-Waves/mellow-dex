/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          DetailsPagerAdapter                                 |
|   Description:    DetailsPagerAdapter class.                          |
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
package me.zaydbille.pokedex.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.fragments.details.AbilityDetails;
import me.zaydbille.pokedex.fragments.details.EggDetails;
import me.zaydbille.pokedex.fragments.details.MainPokemonDetails;
import me.zaydbille.pokedex.fragments.details.PokemonTypeDetails;
import me.zaydbille.pokedex.fragments.details.SkillsDetails;

public class DetailsPagerAdapter extends FragmentStatePagerAdapter {

    private int                             mNumOfTabs;
    private Context                         context;
    private Pokemon                         pokemon;

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
                PokemonTypeDetails tab = PokemonTypeDetails.newInstance(pokemon, context);
                return tab;
            case 1:
                SkillsDetails tab1 = SkillsDetails.newInstance(pokemon, context);
                return tab1;
            case 2:
                MainPokemonDetails tab2 = MainPokemonDetails.newInstance(pokemon, context);
                return tab2;
            case 3:
                AbilityDetails tab3 = AbilityDetails.newInstance(pokemon, context);
                return tab3;
            case 4:
                AbilityDetails tab4 = AbilityDetails.newInstance(pokemon, context);
                return tab4;
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