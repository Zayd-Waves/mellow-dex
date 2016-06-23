package me.zaydbille.pokedex.database;

/**
 * Created by Zayd on 5/28/16.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.zaydbille.pokedex.data.Ability;
import me.zaydbille.pokedex.data.Move;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.utils.SpriteUtils;

public class DatabaseManager {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseManager instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseManager(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<Pokemon> getPokemon() {
        List<Pokemon> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM pokemon", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Pokemon p = new Pokemon();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int speciesNumber = cursor.getInt(2);
            int height = cursor.getInt(3);
            int weight = cursor.getInt(4);
            int baseExperience = cursor.getInt(5);
            int order = cursor.getInt(6);
            String typeOne = null;
            String typeTwo = null;
            if(!cursor.isNull(8)) {
                typeOne = cursor.getString(8);
            }
            if (!cursor.isNull(9)) {
                if(!(cursor.getString(9).equals(""))) {
                    typeTwo = cursor.getString(9);
                }
            }
            int hp = cursor.getInt(10);
            int attack = cursor.getInt(11);
            int defense = cursor.getInt(12);
            int specialAttack = cursor.getInt(13);
            int specialDefense = cursor.getInt(14);
            int speed = cursor.getInt(15);

            p.setId(id);
            p.setRawName(name);
            p.setSpeciesNumber(speciesNumber);
            p.setHeight(height);
            p.setWeight(weight);
            p.setBaseExperience(baseExperience);
            p.setOrder(order);
            p.setTypeOne(typeOne);
            p.setTypeTwo(typeTwo);
            p.setSpriteId(SpriteUtils.getSprite(p.getId(), p.getRawName()));
            p.setShinySpriteId(SpriteUtils.getShinySprite(p.getId(), p.getRawName()));

            p.setBaseHP(hp);
            p.setBaseAttack(attack);
            p.setBaseDefense(defense);
            p.setBaseSpecialAttack(specialAttack);
            p.setBaseSpecialDefense(specialDefense);
            p.setBaseSpeed(speed);

            list.add(p);
            cursor.moveToNext();
        }
        cursor.close();

        /* Adding abilities to the Pokemon */
        HashMap<Integer, ArrayList<Ability>> pokemonAbilities = new HashMap<>();
        Cursor abilityCursor = database.rawQuery("SELECT * FROM pokemon_abilities JOIN abilities WHERE pokemon_abilities.ability_id == abilities.id;", null);
        abilityCursor.moveToFirst();
        while (!abilityCursor.isAfterLast()) {
            int pokemonId = abilityCursor.getInt(0);
            int abilityId = abilityCursor.getInt(1);
            int abilityType = abilityCursor.getInt(2);
            String name = abilityCursor.getString(5);
            String description = abilityCursor.getString(8);

            Ability a = new Ability();
            a.setAbilityId(abilityId);
            a.setType(abilityType);
            a.setName(name);
            a.setDescription(description);

            if(pokemonAbilities.get(pokemonId) == null) {
                ArrayList<Ability> ab = new ArrayList<>();
                ab.add(a);
                pokemonAbilities.put(pokemonId, ab);
            } else {
                ArrayList<Ability> ab = new ArrayList<>();
                ab.add(a);
                ab.addAll(pokemonAbilities.get(pokemonId));
                pokemonAbilities.put(pokemonId, ab);
            }

            abilityCursor.moveToNext();
        }
        abilityCursor.close();

        for (int i = 0; i < list.size(); i++) {
            list.get(i).addAllAbilities(pokemonAbilities.get(list.get(i).getId()));
        }

        /* Adding moves to the pokemon */
        HashMap<Integer, ArrayList<Move>> pokemonMoves = new HashMap<>();
        Cursor moveCursor = database.rawQuery("SELECT * FROM pokemon_moves JOIN moves ON pokemon_moves.move_id = moves.id WHERE pokemon_moves.version_group_id = 9;", null);
        moveCursor.moveToFirst();
        while (!moveCursor.isAfterLast()) {



            moveCursor.moveToNext();
        }
        moveCursor.close();

        for (int i = 0; i < list.size(); i++) {
            list.get(i).addAllMoves(pokemonMoves.get(list.get(i).getId()));
        }

        return list;
    }

    public List<Move> getMoves() {
        List<Move> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM moves", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Move m = new Move();
            int id = cursor.getInt(0);

            if (!(id > 10000)) {
                String name = cursor.getString(1);
                String type = cursor.getString(3);
                int category = cursor.getInt(9);
                Integer power = cursor.getInt(4);
                Integer accuracy = cursor.getInt(6);
                Integer pp = cursor.getInt(5);
                Integer effectChance = cursor.getInt(11);
                String description = cursor.getString(15);

                m.setRawName(name);
                m.setType(type);
                m.setAccuracy(accuracy);
                m.setCategory(category);
                m.setDescription(description);
                m.setPower(power);
                m.setEffectChance(effectChance);
                m.setPp(pp);

                list.add(m);
            }

            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<Ability> getAbilities() {
        List<Ability> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM abilities", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ability a = new Ability();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int mainSeries = cursor.getInt(3);
            String description = cursor.getString(4);

            if (mainSeries == 1) {
                a.setAbilityId(id);
                a.setName(name);
                a.setDescription(description);
                list.add(a);
            } else {
                cursor.moveToNext();
                continue;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    private List<Pokemon> addAbilitiesToPokemon(List<Pokemon> pokemon) {
        List<Pokemon> pokemonWithAbilities = pokemon;
        Cursor abilityCursor = database.rawQuery("SELECT * FROM pokemon JOIN (abilities JOIN pokemon_ability using (ability_name)) USING (pokedex_number)", null);
        abilityCursor.moveToFirst();
        while(!abilityCursor.isAfterLast()) {
            Ability a = new Ability();
            int pokedexNumber = abilityCursor.getInt(0);
            String pokemonName = abilityCursor.getString(1);
            String abilityName = abilityCursor.getString(4);
            if (pokemonName.substring(0, 4).toLowerCase().trim().equals("mega")) {
                abilityCursor.moveToNext();
                continue;
            }
            a.setName(abilityName);
            a.setDescription(abilityCursor.getString(5));
            a.setType(abilityCursor.getInt(6));
            Pokemon p = pokemonWithAbilities.get(pokedexNumber - 1);
            p.addAbility(a);
            abilityCursor.moveToNext();
        }
        abilityCursor.close();
        return pokemonWithAbilities;
    }

    private List<Pokemon> addMegaAbilities(List<Pokemon> pokemon) {
        List<Pokemon> pokemonWithMegaAbilities = pokemon;
        Cursor megaAbilityCursor = database.rawQuery("SELECT * FROM mega_pokemon_abilities JOIN abilities USING (ability_name)", null);
        megaAbilityCursor.moveToFirst();
        while(!megaAbilityCursor.isAfterLast()) {
            Ability a =  new Ability();
            int pokedexNumber = megaAbilityCursor.getInt(0);
            String pokemonName = megaAbilityCursor.getString(1);
            String abilityName = megaAbilityCursor.getString(2);
            String description = megaAbilityCursor.getString(3);
            a.setName(abilityName);
            a.setType(1);
            a.setDescription(description);
            Pokemon p = pokemonWithMegaAbilities.get(pokedexNumber - 1);
            boolean foundMega = false;
            while (!(foundMega)) {
                if (p.getName().toLowerCase().equals(pokemonName.toLowerCase())) {
                    p.addAbility(a);
                    foundMega = true;
                } else {
                    p = pokemonWithMegaAbilities.get((pokedexNumber + 1) - 1);
                }
            }
            megaAbilityCursor.moveToNext();
        }
        megaAbilityCursor.close();
        return pokemonWithMegaAbilities;
    }
}