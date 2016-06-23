package me.zaydbille.pokedex.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.zaydbille.pokedex.R;

/**
 * Created by Zayd on 6/03/16.
 */
public final class TypeUtils {

    public static String[] TYPES = {"normal",
                                    "fighting",
                                    "flying",
                                    "poison",
                                    "ground",
                                    "rock",
                                    "bug",
                                    "ghost",
                                    "steel",
                                    "fire",
                                    "water",
                                    "grass",
                                    "electric",
                                    "psychic",
                                    "ice",
                                    "dragon",
                                    "dark",
                                    "fairy"};

    public static HashMap<String, Double> getCoverageDefenseDual(String type1, String type2) {
        HashMap<String, Double> coverageDefense = new HashMap<String, Double>();

        if (!Arrays.asList(TYPES).contains(type1.toLowerCase()) && !Arrays.asList(TYPES).contains(type2.toLowerCase())) {
            return coverageDefense;
        } else if (Arrays.asList(TYPES).contains(type1.toLowerCase()) && !Arrays.asList(TYPES).contains(type2.toLowerCase())) {
            return getCoverageDefense(type1);
        } else if (!Arrays.asList(TYPES).contains(type1.toLowerCase()) && Arrays.asList(TYPES).contains(type2.toLowerCase())) {
            return getCoverageDefense(type2);
        } else {
            HashMap<String, Double> typeOneDefenses = getCoverageDefense(type1);
            HashMap<String, Double> typeTwoDefenses = getCoverageDefense(type2);
            HashMap<String, Double> iteratingMap = new HashMap<String, Double>();



            if (typeOneDefenses.size() > typeTwoDefenses.size()) {
                coverageDefense.putAll(typeOneDefenses);
                iteratingMap.putAll(typeTwoDefenses);
            } else if (typeOneDefenses.size() == typeTwoDefenses.size()) {
                coverageDefense.putAll(typeOneDefenses);
                iteratingMap.putAll(typeTwoDefenses);
            } else {
                coverageDefense.putAll(typeTwoDefenses);
                iteratingMap.putAll(typeOneDefenses);
            }



            for (Map.Entry<String, Double> entry : iteratingMap.entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();

                if (coverageDefense.get(key) == null) {
                    coverageDefense.put(key, value);
                } else {
                    if (coverageDefense.get(key) == 0 || value == 0.0) {
                        coverageDefense.put(key, 0.0);
                    } else if (coverageDefense.get(key) < 1.0 && value < 1.0) {
                        coverageDefense.put(key, 0.25);
                    } else if (coverageDefense.get(key) < 1.0 && value > 1.0) {
                        coverageDefense.remove(key);
                    } else if (coverageDefense.get(key) > 1.0 && value < 1.0) {
                        coverageDefense.remove(key);
                    } else if (coverageDefense.get(key) > 1.0 && value > 1.0) {
                        coverageDefense.put(key, 4.0);
                    }
                }
            }



            return coverageDefense;
        }
    }

    public static HashMap<String, Double> getCoverageOffenseDual(String type1, String type2) {
        HashMap<String, Double> coverageOffense = new HashMap<String, Double>();

        if (!Arrays.asList(TYPES).contains(type1.toLowerCase()) && !Arrays.asList(TYPES).contains(type2.toLowerCase())) {
            return coverageOffense;
        } else if (Arrays.asList(TYPES).contains(type1.toLowerCase()) && !Arrays.asList(TYPES).contains(type2.toLowerCase())) {
            return getCoverageOffense(type1);
        } else if (!Arrays.asList(TYPES).contains(type1.toLowerCase()) && Arrays.asList(TYPES).contains(type2.toLowerCase())) {
            return getCoverageOffense(type2);
        } else {
            HashMap<String, Double> typeOneOffenses = getCoverageOffense(type1);
            HashMap<String, Double> typeTwoOffenses = getCoverageOffense(type2);
            HashMap<String, Double> iteratingMap = new HashMap<String, Double>();

            if (typeOneOffenses.size() > typeTwoOffenses.size()) {
                coverageOffense.putAll(typeOneOffenses);
                iteratingMap.putAll(typeTwoOffenses);
            } else if (typeOneOffenses.size() == typeTwoOffenses.size()) {
                coverageOffense.putAll(typeOneOffenses);
                iteratingMap.putAll(typeTwoOffenses);
            } else {
                coverageOffense.putAll(typeTwoOffenses);
                iteratingMap.putAll(typeTwoOffenses);
            }

            for (Map.Entry<String, Double> entry : iteratingMap.entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();

                if (coverageOffense.get(key) == null) {
                    coverageOffense.put(key, value);
                }
                else {
                    if (coverageOffense.get(key) == 0.0 || value == 1.0) {
                        coverageOffense.put(key, 0.0);
                    } else if (coverageOffense.get(key) < 1.0 && value < 1.0) {
                        coverageOffense.put(key, 0.5);
                    } else if (coverageOffense.get(key) < 1.0 && value > 1.0) {
                        coverageOffense.remove(key);
                    } else if (coverageOffense.get(key) > 1.0 && value < 1.0) {
                        coverageOffense.remove(key);
                    } else if (coverageOffense.get(key) > 1.0 && value > 1.0) {
                        coverageOffense.put(key, 2.0);
                    }
                }
            }
            return coverageOffense;
        }
    }

    public static HashMap<String, Double> getCoverageDefense(String type) {
        HashMap<String, Double> coverageDefense = new HashMap<String, Double>();

        if (type.toLowerCase().equals("normal")) {
            coverageDefense.put("fighting", 2.0);
            coverageDefense.put("ghost", 0.0);

        } else if (type.toLowerCase().equals("fire")) {
            coverageDefense.put("ground", 2.0);
            coverageDefense.put("rock", 2.0);
            coverageDefense.put("water", 2.0);
            coverageDefense.put("bug", 0.5);
            coverageDefense.put("steel", 0.5);
            coverageDefense.put("fire", 0.5);
            coverageDefense.put("grass", 0.5);
            coverageDefense.put("ice", 0.5);
            coverageDefense.put("fairy", 0.5);

        } else if (type.toLowerCase().equals("fighting")) {
            coverageDefense.put("flying", 2.0);
            coverageDefense.put("psychic", 2.0);
            coverageDefense.put("fairy", 2.0);
            coverageDefense.put("rock", 0.5);
            coverageDefense.put("bug", 0.5);
            coverageDefense.put("dark", 0.5);

        } else if (type.toLowerCase().equals("water")) {
            coverageDefense.put("grass", 2.0);
            coverageDefense.put("electric", 2.0);
            coverageDefense.put("steel", 0.5);
            coverageDefense.put("fire", 0.5);
            coverageDefense.put("water", 0.5);
            coverageDefense.put("ice", 0.5);

        } else if (type.toLowerCase().equals("flying")) {
            coverageDefense.put("rock", 2.0);
            coverageDefense.put("electric", 2.0);
            coverageDefense.put("ice", 2.0);
            coverageDefense.put("ground", 0.0);
            coverageDefense.put("fighting", 0.5);
            coverageDefense.put("bug", 0.5);
            coverageDefense.put("grass", 0.5);

        } else if (type.toLowerCase().equals("grass")) {
            coverageDefense.put("flying", 2.0);
            coverageDefense.put("poison", 2.0);
            coverageDefense.put("bug", 2.0);
            coverageDefense.put("fire", 2.0);
            coverageDefense.put("ice", 2.0);
            coverageDefense.put("ground", 0.5);
            coverageDefense.put("water", 0.5);
            coverageDefense.put("grass", 0.5);
            coverageDefense.put("electric", 0.5);

        } else if (type.toLowerCase().equals("poison")) {
            coverageDefense.put("ground", 2.0);
            coverageDefense.put("psychic", 2.0);
            coverageDefense.put("fighting", 0.5);
            coverageDefense.put("poison", 0.5);
            coverageDefense.put("bug", 0.5);
            coverageDefense.put("grass", 0.5);
            coverageDefense.put("fairy", 0.5);

        } else if (type.toLowerCase().equals("electric")) {
            coverageDefense.put("ground", 2.0);
            coverageDefense.put("flying", 0.5);
            coverageDefense.put("steel", 0.5);
            coverageDefense.put("electric", 0.5);

        } else if (type.toLowerCase().equals("ground")) {
            coverageDefense.put("water", 2.0);
            coverageDefense.put("grass", 2.0);
            coverageDefense.put("ice", 2.0);
            coverageDefense.put("electric", 0.0);
            coverageDefense.put("poison", 0.5);
            coverageDefense.put("rock", 0.5);

        } else if (type.toLowerCase().equals("psychic")) {
            coverageDefense.put("bug", 2.0);
            coverageDefense.put("ghost", 2.0);
            coverageDefense.put("dark", 2.0);
            coverageDefense.put("fighting", 0.5);
            coverageDefense.put("psychic", 0.5);

        } else if (type.toLowerCase().equals("rock")) {
            coverageDefense.put("fighting", 2.0);
            coverageDefense.put("ground", 2.0);
            coverageDefense.put("steel", 2.0);
            coverageDefense.put("water", 2.0);
            coverageDefense.put("grass", 2.0);
            coverageDefense.put("normal", 0.5);
            coverageDefense.put("flying", 0.5);
            coverageDefense.put("poison", 0.5);
            coverageDefense.put("fire", 0.5);

        } else if (type.toLowerCase().equals("ice")) {
            coverageDefense.put("fighting", 2.0);
            coverageDefense.put("rock", 2.0);
            coverageDefense.put("steel", 2.0);
            coverageDefense.put("fire", 2.0);
            coverageDefense.put("ice", 0.5);

        } else if (type.toLowerCase().equals("bug")) {
            coverageDefense.put("flying", 2.0);
            coverageDefense.put("rock", 2.0);
            coverageDefense.put("fire", 2.0);
            coverageDefense.put("fighting", 0.5);
            coverageDefense.put("ground", 0.5);
            coverageDefense.put("grass", 0.5);

        } else if (type.toLowerCase().equals("dragon")) {
            coverageDefense.put("ice", 2.0);
            coverageDefense.put("dragon", 2.0);
            coverageDefense.put("fairy", 2.0);
            coverageDefense.put("fire", 0.5);
            coverageDefense.put("water", 0.5);
            coverageDefense.put("grass", 0.5);
            coverageDefense.put("electric", 0.5);

        } else if (type.toLowerCase().equals("ghost")) {
            coverageDefense.put("ghost", 2.0);
            coverageDefense.put("dark", 2.0);
            coverageDefense.put("normal", 0.0);
            coverageDefense.put("fighting", 0.0);
            coverageDefense.put("poison", 0.5);
            coverageDefense.put("bug", 0.5);

        } else if (type.toLowerCase().equals("dark")) {
            coverageDefense.put("fighting", 2.0);
            coverageDefense.put("bug", 2.0);
            coverageDefense.put("fairy", 2.0);
            coverageDefense.put("psychic", 0.0);
            coverageDefense.put("ghost", 0.5);
            coverageDefense.put("dark", 0.5);

        } else if (type.toLowerCase().equals("steel")) {
            coverageDefense.put("fighting", 2.0);
            coverageDefense.put("ground", 2.0);
            coverageDefense.put("fire", 2.0);
            coverageDefense.put("poison", 0.0);
            coverageDefense.put("normal", 0.5);
            coverageDefense.put("flying", 0.5);
            coverageDefense.put("rock", 0.5);
            coverageDefense.put("bug", 0.5);
            coverageDefense.put("steel", 0.5);
            coverageDefense.put("grass", 0.5);
            coverageDefense.put("psychic", 0.5);
            coverageDefense.put("ice", 0.5);
            coverageDefense.put("dragon", 0.5);
            coverageDefense.put("fairy", 0.5);

        } else if (type.toLowerCase().equals("fairy")) {
            coverageDefense.put("poison", 2.0);
            coverageDefense.put("steel", 2.0);
            coverageDefense.put("dragon", 0.0);
            coverageDefense.put("fighting", 0.5);
            coverageDefense.put("bug", 0.5);
            coverageDefense.put("dark", 0.5);

        }

        return coverageDefense;
    }

    public static HashMap<String, Double> getCoverageOffense(String type) {
        HashMap<String, Double> coverageOffense = new HashMap<String, Double>();

        if (type.toLowerCase().equals("normal")) {
            coverageOffense.put("ghost", 0.0);
            coverageOffense.put("rock", 0.5);
            coverageOffense.put("steel", 0.5);

        } else if (type.toLowerCase().equals("fire")) {
            coverageOffense.put("bug", 2.0);
            coverageOffense.put("steel", 2.0);
            coverageOffense.put("grass", 2.0);
            coverageOffense.put("ice", 2.0);
            coverageOffense.put("rock", 0.5);
            coverageOffense.put("fire", 0.5);
            coverageOffense.put("water", 0.5);
            coverageOffense.put("dragon", 0.5);

        } else if (type.toLowerCase().equals("fighting")) {
            coverageOffense.put("normal", 2.0);
            coverageOffense.put("rock", 2.0);
            coverageOffense.put("steel", 2.0);
            coverageOffense.put("ice", 2.0);
            coverageOffense.put("dark", 2.0);
            coverageOffense.put("ghost", 0.0);
            coverageOffense.put("flying", 0.5);
            coverageOffense.put("poison", 0.5);
            coverageOffense.put("bug", 0.5);
            coverageOffense.put("psychic", 0.5);
            coverageOffense.put("fairy", 0.5);

        } else if (type.toLowerCase().equals("water")) {
            coverageOffense.put("ground", 2.0);
            coverageOffense.put("rock", 2.0);
            coverageOffense.put("fire", 2.0);
            coverageOffense.put("water", 0.5);
            coverageOffense.put("grass", 0.5);
            coverageOffense.put("dragon", 0.5);

        } else if (type.toLowerCase().equals("flying")) {
            coverageOffense.put("fighting", 2.0);
            coverageOffense.put("bug", 2.0);
            coverageOffense.put("grass", 2.0);
            coverageOffense.put("rock", 0.5);
            coverageOffense.put("steel", 0.5);
            coverageOffense.put("electric", 0.5);

        } else if (type.toLowerCase().equals("grass")) {
            coverageOffense.put("ground", 2.0);
            coverageOffense.put("rock", 2.0);
            coverageOffense.put("water", 2.0);
            coverageOffense.put("flying", 2.0);
            coverageOffense.put("poison", 0.5);
            coverageOffense.put("bug", 0.5);
            coverageOffense.put("steel", 0.5);
            coverageOffense.put("fire", 0.5);
            coverageOffense.put("grass", 0.5);
            coverageOffense.put("dragon", 0.5);

        } else if (type.toLowerCase().equals("poison")) {
            coverageOffense.put("grass", 2.0);
            coverageOffense.put("fairy", 2.0);
            coverageOffense.put("steel", 0.0);
            coverageOffense.put("poison", 0.5);
            coverageOffense.put("ground", 0.5);
            coverageOffense.put("rock", 0.5);
            coverageOffense.put("ghost", 0.5);

        } else if (type.toLowerCase().equals("electric")) {
            coverageOffense.put("flying", 2.0);
            coverageOffense.put("water", 2.0);
            coverageOffense.put("ground", 0.0);
            coverageOffense.put("grass", 0.5);
            coverageOffense.put("electric", 0.5);
            coverageOffense.put("dragon", 0.5);

        } else if (type.toLowerCase().equals("ground")) {
            coverageOffense.put("poison", 2.0);
            coverageOffense.put("rock", 2.0);
            coverageOffense.put("steel", 2.0);
            coverageOffense.put("fire", 2.0);
            coverageOffense.put("electric", 2.0);
            coverageOffense.put("flying", 0.0);
            coverageOffense.put("bug", 0.5);
            coverageOffense.put("grass", 0.5);

        } else if (type.toLowerCase().equals("psychic")) {
            coverageOffense.put("fighting", 2.0);
            coverageOffense.put("poison", 2.0);
            coverageOffense.put("dark", 0.0);
            coverageOffense.put("steel", 0.5);
            coverageOffense.put("psychic", 0.5);

        } else if (type.toLowerCase().equals("rock")) {
            coverageOffense.put("flying", 2.0);
            coverageOffense.put("bug", 2.0);
            coverageOffense.put("ice", 2.0);
            coverageOffense.put("fire", 2.0);
            coverageOffense.put("fighting", 0.5);
            coverageOffense.put("ground", 0.5);
            coverageOffense.put("steel", 0.5);

        } else if (type.toLowerCase().equals("ice")) {
            coverageOffense.put("flying", 2.0);
            coverageOffense.put("ground", 2.0);
            coverageOffense.put("grass", 2.0);
            coverageOffense.put("dragon", 2.0);
            coverageOffense.put("steel", 0.5);
            coverageOffense.put("fire", 0.5);
            coverageOffense.put("water", 0.5);
            coverageOffense.put("ice", 0.5);

        } else if (type.toLowerCase().equals("bug")) {
            coverageOffense.put("grass", 2.0);
            coverageOffense.put("psychic", 2.0);
            coverageOffense.put("dark", 2.0);
            coverageOffense.put("fighting", 0.5);
            coverageOffense.put("flying", 0.5);
            coverageOffense.put("poison", 0.5);
            coverageOffense.put("ghost", 0.5);
            coverageOffense.put("steel", 0.5);
            coverageOffense.put("fire", 0.5);
            coverageOffense.put("fairy", 0.5);

        } else if (type.toLowerCase().equals("dragon")) {
            coverageOffense.put("dragon", 2.0);
            coverageOffense.put("fairy", 0.0);
            coverageOffense.put("steel", 0.5);

        } else if (type.toLowerCase().equals("ghost")) {
            coverageOffense.put("ghost", 2.0);
            coverageOffense.put("psychic", 2.0);
            coverageOffense.put("normal", 0.0);
            coverageOffense.put("dark", 0.5);

        } else if (type.toLowerCase().equals("dark")) {
            coverageOffense.put("ghost", 2.0);
            coverageOffense.put("psychic", 2.0);
            coverageOffense.put("fighting", 0.5);
            coverageOffense.put("dark", 0.5);
            coverageOffense.put("fairy", 0.5);

        } else if (type.toLowerCase().equals("steel")) {
            coverageOffense.put("rock", 2.0);
            coverageOffense.put("ice", 2.0);
            coverageOffense.put("fairy", 2.0);
            coverageOffense.put("steel", 0.5);
            coverageOffense.put("fire", 0.5);
            coverageOffense.put("water", 0.5);
            coverageOffense.put("electric", 0.5);

        } else if (type.toLowerCase().equals("fairy")) {
            coverageOffense.put("fighting", 2.0);
            coverageOffense.put("dragon", 2.0);
            coverageOffense.put("dark", 2.0);
            coverageOffense.put("poison", 0.5);
            coverageOffense.put("steel", 0.5);
            coverageOffense.put("fire", 0.5);

        }

        return coverageOffense;
    }

    public static int getTypeColour(String type, Context context) {
        int c = 0;

        if (type.toLowerCase().equals("normal")) {
            c = ContextCompat.getColor(context, R.color.normal);
        } else if (type.toLowerCase().equals("fire")) {
            c = ContextCompat.getColor(context, R.color.fire);
        } else if (type.toLowerCase().equals("fighting")) {
            c = ContextCompat.getColor(context, R.color.fighting);
        } else if (type.toLowerCase().equals("water")) {
            c = ContextCompat.getColor(context, R.color.water);
        } else if (type.toLowerCase().equals("flying")) {
            c = ContextCompat.getColor(context, R.color.flying);
        } else if (type.toLowerCase().equals("grass")) {
            c = ContextCompat.getColor(context, R.color.grass);
        } else if (type.toLowerCase().equals("poison")) {
            c = ContextCompat.getColor(context, R.color.poison);
        } else if (type.toLowerCase().equals("electric")) {
            c = ContextCompat.getColor(context, R.color.electric);
        } else if (type.toLowerCase().equals("ground")) {
            c = ContextCompat.getColor(context, R.color.ground);
        } else if (type.toLowerCase().equals("psychic")) {
            c = ContextCompat.getColor(context, R.color.psychic);
        } else if (type.toLowerCase().equals("rock")) {
            c = ContextCompat.getColor(context, R.color.rock);
        } else if (type.toLowerCase().equals("ice")) {
            c = ContextCompat.getColor(context, R.color.ice);
        } else if (type.toLowerCase().equals("bug")) {
            c = ContextCompat.getColor(context, R.color.bug);
        } else if (type.toLowerCase().equals("dragon")) {
            c = ContextCompat.getColor(context, R.color.dragon);
        } else if (type.toLowerCase().equals("ghost")) {
            c = ContextCompat.getColor(context, R.color.ghost);
        } else if (type.toLowerCase().equals("dark")) {
            c = ContextCompat.getColor(context, R.color.dark);
        } else if (type.toLowerCase().equals("steel")) {
            c = ContextCompat.getColor(context, R.color.steel);
        } else if (type.toLowerCase().equals("fairy")) {
            c = ContextCompat.getColor(context, R.color.fairy);
        }

        return c;
    }

}
