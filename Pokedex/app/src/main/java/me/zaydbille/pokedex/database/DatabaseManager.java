/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          DatabaseManager                                     |
|   Description:    DatabaseManager class.                              |
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
package me.zaydbille.pokedex.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.zaydbille.pokedex.data.Ability;
import me.zaydbille.pokedex.data.Move;
import me.zaydbille.pokedex.data.Pokemon;
import me.zaydbille.pokedex.storage.PreferencesManager;
import me.zaydbille.pokedex.utils.SpriteUtils;

public class DatabaseManager {
    private SQLiteOpenHelper                            openHelper;
    private SQLiteDatabase                              database;
    private static DatabaseManager                      instance;

    /* Private constructor to avoid object creation from outside classes. */
    private DatabaseManager(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /* Return a singleton instance of DatabaseAccess. */
    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    /* Open the database connection. */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /* Close the database connection. */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /* Read all Pokemon from the database. */
    public List<Pokemon> getPokemon() {
        List<Pokemon> list = new ArrayList<>();

        /*Cursor cursor = database.rawQuery("SELECT * FROM pokemon JOIN (pokemon_abilities JOIN abilities ON pokemon_abilities.ability_id == abilities.id) WHERE pokemon.id == pokemon_abilities.pokemon_id;", null);
        cursor.moveToFirst();
        int lastPokemonAddedId = -1;
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            if (lastPokemonAddedId == id) {
                // Just need to add the ability
                // Add the ability
                Pokemon p = list.get(list.size() - 1);
                int abilityId = cursor.getInt(17);
                int abilityType = cursor.getInt(18);
                String abilityName = cursor.getString(21);
                String abilityDescription = cursor.getString(24);

                Ability a = new Ability();
                a.setAbilityId(abilityId);
                a.setType(abilityType);
                a.setName(abilityName);
                a.setDescription(abilityDescription);
                p.addAbility(a);
            } else {
                // Need to create a new Pokemon
                Pokemon p = new Pokemon();
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

                // Add the ability
                int abilityId = cursor.getInt(17);
                int abilityType = cursor.getInt(18);
                String abilityName = cursor.getString(21);
                String abilityDescription = cursor.getString(24);

                Ability a = new Ability();
                a.setAbilityId(abilityId);
                a.setType(abilityType);
                a.setName(abilityName);
                a.setDescription(abilityDescription);
                p.addAbility(a);

                list.add(p);
            }
            cursor.moveToNext();
            lastPokemonAddedId = id;
        }*/

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
            //p.setShinySpriteId(SpriteUtils.getShinySprite(p.getId(), p.getRawName()));

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


        /* Adding abilities to the Pokemon
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
        */

        /*
            Getting the TM/HM's list.
            The keys are the move ID's and the values are the machine numbers.
        */

        /*
        HashMap<Integer, Integer> machines = new HashMap<>();
        Cursor machineCursor = database.rawQuery("SELECT * FROM machines WHERE machines.version_group_id = 16;", null);
        machineCursor.moveToFirst();
        while (!machineCursor.isAfterLast()) {
            int moveID = machineCursor.getInt(2);
            int machineNumber = machineCursor.getInt(0);
            machines.put(moveID, machineNumber);
            machineCursor.moveToNext();
        }
        machineCursor.close();

        */

        /* Adding moves to the pokemon
        HashMap<Integer, ArrayList<Move>> pokemonMoves = new HashMap<>();
        Cursor moveCursor = database.rawQuery("SELECT pokemon_move_methods.identifier, pokemon_moves.pokemon_id, pokemon_moves.move_id, pokemon_moves.level, moves.identifier, moves.type, moves.power, moves.pp, moves.accuracy, moves.priority, moves.target_id, moves.damage_class_id, moves.effect_id, moves.effect_chance, moves.description FROM pokemon_move_methods JOIN (pokemon_moves JOIN moves ON pokemon_moves.move_id = moves.id) ON pokemon_move_methods.id = pokemon_moves.pokemon_move_method_id WHERE pokemon_moves.version_group_id = 16;", null);
        moveCursor.moveToFirst();
        while (!moveCursor.isAfterLast()) {
            String learnType = moveCursor.getString(0);
            int pokemonID = moveCursor.getInt(1);
            int moveID = moveCursor.getInt(2);
            int level = moveCursor.getInt(3);
            String moveName = moveCursor.getString(4);
            String moveType = moveCursor.getString(5);
            int power = 0;
            int pp = 0;
            int accuracy = 0;
            int priority = 0;
            int targetID = 0;
            int damageClassID = 0;
            int effectChance = 0;
            if (!moveCursor.isNull(6)) {
                power = moveCursor.getInt(6);
            }
            if(!moveCursor.isNull(7)){
                pp = moveCursor.getInt(7);
            }
            if(!moveCursor.isNull(8)){
                accuracy = moveCursor.getInt(8);
            }
            if(!moveCursor.isNull(9)){
                priority = moveCursor.getInt(9);
            }
            if(!moveCursor.isNull(10)){
                targetID = moveCursor.getInt(10);
            }
            if(!moveCursor.isNull(11)){
                damageClassID = moveCursor.getInt(11);
            }
            if(!moveCursor.isNull(13)){
                effectChance = moveCursor.getInt(13);
            }
            String description = moveCursor.getString(14);

            Move m = new Move();
            m.setLearnType(learnType);
            m.setId(moveID);
            m.setLevel(level);
            m.setRawName(moveName);
            m.setType(moveType);
            m.setPower(power);
            m.setPp(pp);
            m.setAccuracy(accuracy);
            m.setPriority(priority);
            m.setTargetId(targetID);
            m.setDamageClass(damageClassID);
            m.setEffectChance(effectChance);
            m.setDescription(description);
            m.setHM(false);

            if ( m.getLearnType().equals("machine") ) {
                m.setTmHmNumber(machines.get(m.getId()));
                if(m.getTmHmNumber() > 100) {
                    m.setHM(true);
                }
            }

            if(pokemonMoves.get(pokemonID) == null) {
                ArrayList<Move> mov = new ArrayList<>();
                mov.add(m);
                pokemonMoves.put(pokemonID, mov);
            } else {
                ArrayList<Move> mov = new ArrayList<>();
                mov.add(m);
                mov.addAll(pokemonMoves.get(pokemonID));
                pokemonMoves.put(pokemonID, mov);
            }
            moveCursor.moveToNext();
        }
        moveCursor.close();

        for (int i = 0; i < list.size(); i++) {
            list.get(i).addAllMoves(pokemonMoves.get(list.get(i).getId()));
        }
        */
        return list;
    }

    public List<Pokemon> getAllPokemonData() {
        List<Pokemon> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM pokemon JOIN (pokemon_abilities JOIN abilities ON pokemon_abilities.ability_id == abilities.id) WHERE pokemon.id == pokemon_abilities.pokemon_id;", null);
        cursor.moveToFirst();
        int lastPokemonAddedId = -1;
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            if (lastPokemonAddedId == id) {
                // Just need to add the ability
                // Add the ability
                Pokemon p = list.get(list.size() - 1);
                int abilityId = cursor.getInt(17);
                int abilityType = cursor.getInt(18);
                String abilityName = cursor.getString(21);
                String abilityDescription = cursor.getString(24);

                Ability a = new Ability();
                a.setAbilityId(abilityId);
                a.setType(abilityType);
                a.setName(abilityName);
                a.setDescription(abilityDescription);
                p.addAbility(a);
            } else {
                // Need to create a new Pokemon
                Pokemon p = new Pokemon();
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
                //p.setShinySpriteId(SpriteUtils.getShinySprite(p.getId(), p.getRawName()));

                p.setBaseHP(hp);
                p.setBaseAttack(attack);
                p.setBaseDefense(defense);
                p.setBaseSpecialAttack(specialAttack);
                p.setBaseSpecialDefense(specialDefense);
                p.setBaseSpeed(speed);

                // Add the ability
                int abilityId = cursor.getInt(17);
                int abilityType = cursor.getInt(18);
                String abilityName = cursor.getString(21);
                String abilityDescription = cursor.getString(24);

                Ability a = new Ability();
                a.setAbilityId(abilityId);
                a.setType(abilityType);
                a.setName(abilityName);
                a.setDescription(abilityDescription);
                p.addAbility(a);

                list.add(p);
            }
            cursor.moveToNext();
            lastPokemonAddedId = id;
        }


        /* Adding abilities to the Pokemon
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
        }*/



        HashMap<Integer, Integer> machines = PreferencesManager.getAllMachines();

        /* Adding moves to the pokemon */
        HashMap<Integer, ArrayList<Move>> pokemonMoves = new HashMap<>();
        Cursor moveCursor = database.rawQuery("SELECT pokemon_move_methods.identifier, pokemon_moves.pokemon_id, pokemon_moves.move_id, pokemon_moves.level, moves.identifier, moves.type, moves.power, moves.pp, moves.accuracy, moves.priority, moves.target_id, moves.damage_class_id, moves.effect_id, moves.effect_chance, moves.description FROM pokemon_move_methods JOIN (pokemon_moves JOIN moves ON pokemon_moves.move_id = moves.id) ON pokemon_move_methods.id = pokemon_moves.pokemon_move_method_id WHERE pokemon_moves.version_group_id = 16;", null);
        moveCursor.moveToFirst();
        while (!moveCursor.isAfterLast()) {
            String learnType = moveCursor.getString(0);
            int pokemonID = moveCursor.getInt(1);
            int moveID = moveCursor.getInt(2);
            int level = moveCursor.getInt(3);
            String moveName = moveCursor.getString(4);
            String moveType = moveCursor.getString(5);
            int power = 0;
            int pp = 0;
            int accuracy = 0;
            int priority = 0;
            int targetID = 0;
            int damageClassID = 0;
            int effectChance = 0;
            if (!moveCursor.isNull(6)) {
                power = moveCursor.getInt(6);
            }
            if(!moveCursor.isNull(7)){
                pp = moveCursor.getInt(7);
            }
            if(!moveCursor.isNull(8)){
                accuracy = moveCursor.getInt(8);
            }
            if(!moveCursor.isNull(9)){
                priority = moveCursor.getInt(9);
            }
            if(!moveCursor.isNull(10)){
                targetID = moveCursor.getInt(10);
            }
            if(!moveCursor.isNull(11)){
                damageClassID = moveCursor.getInt(11);
            }
            if(!moveCursor.isNull(13)){
                effectChance = moveCursor.getInt(13);
            }
            String description = moveCursor.getString(14);

            Move m = new Move();
            m.setLearnType(learnType);
            m.setId(moveID);
            m.setLevel(level);
            m.setRawName(moveName);
            m.setType(moveType);
            m.setPower(power);
            m.setPp(pp);
            m.setAccuracy(accuracy);
            m.setPriority(priority);
            m.setTargetId(targetID);
            m.setDamageClass(damageClassID);
            m.setEffectChance(effectChance);
            m.setDescription(description);
            m.setHM(false);

            if ( m.getLearnType().equals("machine") ) {
                m.setTmHmNumber(machines.get(m.getId()));
                if(m.getTmHmNumber() > 100) {
                    m.setHM(true);
                }
            }

            if(pokemonMoves.get(pokemonID) == null) {
                ArrayList<Move> mov = new ArrayList<>();
                mov.add(m);
                pokemonMoves.put(pokemonID, mov);
            } else {
                ArrayList<Move> mov = new ArrayList<>();
                mov.add(m);
                mov.addAll(pokemonMoves.get(pokemonID));
                pokemonMoves.put(pokemonID, mov);
            }
            moveCursor.moveToNext();
        }
        moveCursor.close();

        for (int i = 0; i < list.size(); i++) {
            list.get(i).addAllMoves(pokemonMoves.get(list.get(i).getId()));
        }

        return list;
    }

    public Pokemon getSinglePokemonData(int pokemonId) {
        Pokemon p = new Pokemon();

        /* Get the Pokemon's abilities. */
        Cursor abilityCursor = database.rawQuery("SELECT * FROM pokemon JOIN (pokemon_abilities JOIN abilities ON pokemon_abilities.ability_id == abilities.id) WHERE pokemon.id == pokemon_abilities.pokemon_id AND pokemon.id == " + pokemonId + ";", null);
        abilityCursor.moveToFirst();
        while (!abilityCursor.isAfterLast()) {
            int id = abilityCursor.getInt(0);
            String name = abilityCursor.getString(1);
            int speciesNumber = abilityCursor.getInt(2);
            int height = abilityCursor.getInt(3);
            int weight = abilityCursor.getInt(4);
            int baseExperience = abilityCursor.getInt(5);
            int order = abilityCursor.getInt(6);
            String typeOne = null;
            String typeTwo = null;
            if(!abilityCursor.isNull(8)) {
                typeOne = abilityCursor.getString(8);
            }
            if (!abilityCursor.isNull(9)) {
                if(!(abilityCursor.getString(9).equals(""))) {
                    typeTwo = abilityCursor.getString(9);
                }
            }
            int hp = abilityCursor.getInt(10);
            int attack = abilityCursor.getInt(11);
            int defense = abilityCursor.getInt(12);
            int specialAttack = abilityCursor.getInt(13);
            int specialDefense = abilityCursor.getInt(14);
            int speed = abilityCursor.getInt(15);

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
            //p.setShinySpriteId(SpriteUtils.getShinySprite(p.getId(), p.getRawName()));

            p.setBaseHP(hp);
            p.setBaseAttack(attack);
            p.setBaseDefense(defense);
            p.setBaseSpecialAttack(specialAttack);
            p.setBaseSpecialDefense(specialDefense);
            p.setBaseSpeed(speed);

            // Add the ability
            int abilityId = abilityCursor.getInt(17);
            int abilityType = abilityCursor.getInt(18);
            String abilityName = abilityCursor.getString(21);
            String abilityDescription = abilityCursor.getString(24);

            Ability a = new Ability();
            a.setAbilityId(abilityId);
            a.setType(abilityType);
            a.setName(abilityName);
            a.setDescription(abilityDescription);
            p.addAbility(a);
            abilityCursor.moveToNext();
        }

        /* Get the Pokemon's moves. */
        /*
            Getting the TM/HM's list.
            The keys are the move ID's and the values are the machine numbers.
        */
        HashMap<Integer, Integer> machines = new HashMap<>();
        Cursor machineCursor = database.rawQuery("SELECT * FROM machines WHERE machines.version_group_id = 16;", null);
        machineCursor.moveToFirst();
        while (!machineCursor.isAfterLast()) {
            int moveID = machineCursor.getInt(2);
            int machineNumber = machineCursor.getInt(0);
            machines.put(moveID, machineNumber);
            machineCursor.moveToNext();
        }
        machineCursor.close();


        /* Adding moves to the pokemon */
        ArrayList<Move> moves = new ArrayList<>();
        Cursor moveCursor = database.rawQuery("SELECT pokemon_move_methods.identifier, pokemon_moves.pokemon_id, pokemon_moves.move_id, pokemon_moves.level, moves.identifier, moves.type, moves.power, moves.pp, moves.accuracy, moves.priority, moves.target_id, moves.damage_class_id, moves.effect_id, moves.effect_chance, moves.description FROM pokemon_move_methods JOIN (pokemon_moves JOIN moves ON pokemon_moves.move_id = moves.id) ON pokemon_move_methods.id = pokemon_moves.pokemon_move_method_id WHERE pokemon_moves.version_group_id = 16  AND pokemon_moves.pokemon_id == " + pokemonId + ";", null);
        moveCursor.moveToFirst();
        while (!moveCursor.isAfterLast()) {
            String learnType = moveCursor.getString(0);
            int pokemonID = moveCursor.getInt(1);
            int moveID = moveCursor.getInt(2);
            int level = moveCursor.getInt(3);
            String moveName = moveCursor.getString(4);
            String moveType = moveCursor.getString(5);
            int power = 0;
            int pp = 0;
            int accuracy = 0;
            int priority = 0;
            int targetID = 0;
            int damageClassID = 0;
            int effectChance = 0;
            if (!moveCursor.isNull(6)) {
                power = moveCursor.getInt(6);
            }
            if(!moveCursor.isNull(7)){
                pp = moveCursor.getInt(7);
            }
            if(!moveCursor.isNull(8)){
                accuracy = moveCursor.getInt(8);
            }
            if(!moveCursor.isNull(9)){
                priority = moveCursor.getInt(9);
            }
            if(!moveCursor.isNull(10)){
                targetID = moveCursor.getInt(10);
            }
            if(!moveCursor.isNull(11)){
                damageClassID = moveCursor.getInt(11);
            }
            if(!moveCursor.isNull(13)){
                effectChance = moveCursor.getInt(13);
            }
            String description = moveCursor.getString(14);

            Move m = new Move();
            m.setLearnType(learnType);
            m.setId(moveID);
            m.setLevel(level);
            m.setRawName(moveName);
            m.setType(moveType);
            m.setPower(power);
            m.setPp(pp);
            m.setAccuracy(accuracy);
            m.setPriority(priority);
            m.setTargetId(targetID);
            m.setDamageClass(damageClassID);
            m.setEffectChance(effectChance);
            m.setDescription(description);
            m.setHM(false);

            if ( m.getLearnType().equals("machine") ) {
                m.setTmHmNumber(machines.get(m.getId()));
                if(m.getTmHmNumber() > 100) {
                    m.setHM(true);
                }
            }


            moves.add(m);
            moveCursor.moveToNext();
        }
        moveCursor.close();
        p.addAllMoves(moves);

        return p;
    }

    /* Read all Moves from the database. */
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

        /*
            Getting the TM/HM's list.
            The keys are the move ID's and the values are the machine numbers.
        */
        HashMap<Integer, Integer> machines = new HashMap<>();
        Cursor machineCursor = database.rawQuery("SELECT * FROM machines WHERE machines.version_group_id = 16;", null);
        machineCursor.moveToFirst();
        while (!machineCursor.isAfterLast()) {
            int moveID = machineCursor.getInt(2);
            int machineNumber = machineCursor.getInt(0);
            machines.put(moveID, machineNumber);
            machineCursor.moveToNext();
        }
        machineCursor.close();
        PreferencesManager.setAllMachines(machines);

        return list;
    }

    /* Read all Abilities from the database. */
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

    /* Read all Pokemon-Ability relations from the database. */
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

    /* Read all Pokemon-MegaAbility relations from the database. */
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