/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          AbilityUtils                                        |
|   Description:    AbilityUtils class.                                 |
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
package me.zaydbille.pokedex.utils;

import java.util.ArrayList;
import java.util.List;
import me.zaydbille.pokedex.data.Ability;

public final class AbilityUtils {

    public static List<Ability> sortAbilitiesByHidden(List<Ability> abilities) {
        List<Ability> abilityList = new ArrayList<>();

        for (int i = 0; i < abilities.size(); i++) {
            if (abilities.get(i).getType() == 0) {
                abilityList.add(abilities.get(i));
            }
        }
        for (int i = 0; i < abilities.size(); i++) {
            if (abilities.get(i).getType() == 1) {
                abilityList.add(abilities.get(i));
            }
        }
        return abilityList;
    }
}
