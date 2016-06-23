package me.zaydbille.pokedex.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.zaydbille.pokedex.data.Ability;
import me.zaydbille.pokedex.data.Move;
import me.zaydbille.pokedex.data.Pokemon;

/**
 * Created by Zayd on 5/29/16.
 */
public final class PokedexUtils {

    public static List<Pokemon> sortByNumber(List<Pokemon> pokemonList) {
        List<Pokemon> sortedList = pokemonList;
        Collections.sort(sortedList);
        return sortedList;
    }

    public static List<Ability> sortAbilitiesByName(List<Ability> abilityList) {
        List<Ability> sortedList = abilityList;
        Collections.sort(sortedList);
        return sortedList;
    }

    public static List<Move> sortMovesByName(List<Move> moveList) {
        List<Move> sortedList = moveList;
        Collections.sort(sortedList);
        return sortedList;
    }

    public static Pokemon[] sortByNumberAndConvertToArray(List<Pokemon> pokemonList) {
        Collections.sort(pokemonList);
        return convertListToArray(pokemonList);
    }

    public static Pokemon[] convertListToArray(List<Pokemon> pokemonList) {
        Pokemon[] pokemonArray = new Pokemon[pokemonList.size()];
        pokemonArray = pokemonList.toArray(pokemonArray);
        return pokemonArray;
    }
}
