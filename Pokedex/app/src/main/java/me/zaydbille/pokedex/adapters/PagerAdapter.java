/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          PagerAdapter                                        |
|   Description:    Pager adapter that manages the main fragments.      |
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
import java.util.List;

import me.zaydbille.pokedex.data.Ability;
import me.zaydbille.pokedex.data.Move;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.fragments.AbilityScreen;
import me.zaydbille.pokedex.fragments.CaughtScreen;
import me.zaydbille.pokedex.fragments.LocationScreen;
import me.zaydbille.pokedex.fragments.MoveScreen;
import me.zaydbille.pokedex.fragments.PokedexScreen;
import me.zaydbille.pokedex.fragments.TeamScreen;
import me.zaydbille.pokedex.fragments.TypeScreen;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    Context context;

    /* Pokemon Data */
    List<Pokemon> pokemon;
    List<Move> moves;
    List<Ability> abilities;

    int caughtRowTextColour;
    int rowColour;

    public PagerAdapter(FragmentManager fm,
                        int NumOfTabs,
                        Context con,
                        List<Pokemon> data,
                        List<Move> moveList,
                        List<Ability> abilityList,
                        int caughtText,
                        int rowCol) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        pokemon = data;
        moves = moveList;
        abilities = abilityList;
        context = con;
        caughtRowTextColour = caughtText;
        rowColour = rowCol;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TypeScreen tab1 = new TypeScreen();
                return tab1;
            case 1:
                MoveScreen tab2 = MoveScreen.newInstance(context, moves);
                return tab2;
            case 2:
                PokedexScreen tab3 = PokedexScreen.newInstance(context, pokemon);
                return tab3;
            case 3:
                AbilityScreen tab4 = AbilityScreen.newInstance(context, abilities);
                return tab4;
            /*case 4:

                LocationScreen tab5 = new LocationScreen();
                return tab5;
            */
            case 4:
                CaughtScreen tab6 = CaughtScreen.newInstance(context, pokemon, caughtRowTextColour, rowColour);
                return tab6;
            case 5:
                TeamScreen tab7 = TeamScreen.newInstance(context);
                return tab7;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}