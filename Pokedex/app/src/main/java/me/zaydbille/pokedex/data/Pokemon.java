/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          Pokemon                                             |
|   Description:    Data class that represents a Pokemon entity.        |
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

package me.zaydbille.pokedex.data;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Pokemon implements Comparable<Pokemon>, Parcelable {

    private int id;
    private String name;
    private int speciesNumber;
    private int height;
    private int weight;
    private int baseExperience;
    private int order;
    private String weightString;
    private String heightString;
    private String typeOne;
    private String typeTwo;
    private ArrayList<Ability> myAbilities = new ArrayList<Ability>();

    public ArrayList<Move> getMyMoves() {
        return myMoves;
    }

    public void addMove(Move m) {
        myMoves.add(m);
    }
    private ArrayList<Move> myMoves = new ArrayList<>();

    public void addAllMoves(ArrayList<Move> moves) {
        this.myMoves = moves;
    }

    public void addAllAbilities(ArrayList<Ability> abilities) {
        this.myAbilities = abilities;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public int getBaseSpecialAttack() {
        return baseSpecialAttack;
    }

    public void setBaseSpecialAttack(int baseSpecialAttack) {
        this.baseSpecialAttack = baseSpecialAttack;
    }

    public int getBaseSpecialDefense() {
        return baseSpecialDefense;
    }

    public void setBaseSpecialDefense(int baseSpecialDefense) {
        this.baseSpecialDefense = baseSpecialDefense;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    private int baseHP;
    private int baseAttack;
    private int baseDefense;
    private int baseSpecialAttack;
    private int baseSpecialDefense;
    private int baseSpeed;

    public int getBaseTotal() {
        return baseHP + baseAttack + baseDefense + baseSpecialAttack + baseSpecialDefense + baseSpeed;
    }

    private int baseTotal;

    public int getSpriteId() {
        return spriteId;
    }

    public void setSpriteId(int spriteId) {
        this.spriteId = spriteId;
    }

    public int getShinySpriteId() {
        return shinySpriteId;
    }

    public void setShinySpriteId(int shinySpriteId) {
        this.shinySpriteId = shinySpriteId;
    }

    private int spriteId;
    private int shinySpriteId;

    public Pokemon() {
    }

    public Pokemon(int num, String na, String type1, String type2) {
        speciesNumber = num;
        name = na;
        typeOne = type1;
        typeTwo = type2;
    }

    public int compareTo(Pokemon p)
    {
        return(speciesNumber -  p.speciesNumber);
    }


    public int getId() {
        return id;
    }
    public int getHeight() {
        return height;
    }
    public int getWeight() {
        return weight;
    }
    public int getBaseExperience() {
        return baseExperience;
    }
    public int getOrder() {
        return order;
    }
    public String getOrderString() { return order + "";}
    public String getWeightString() {
        return weightString;
    }
    public String getHeightString() {
        return heightString;
    }
    public int getSpeciesNumber() {
        return speciesNumber;
    }
    public String getNumberString() { return pokedexNumberRepresentation(speciesNumber); }
    public String getName() {
        return formatName(name);
    }
    public String getRawName() { return name; }
    public String getTypeOne() {
        return typeOne;
    }
    public String getTypeTwo() {
        return typeTwo;
    }
    public ArrayList<Ability> getMyAbilities() { return myAbilities; }

    public void setId(int id) {
        this.id = id;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public void setWeightString(String weightString) {
        this.weightString = weightString;
    }
    public void setHeightString(String heightString) {
        this.heightString = heightString;
    }
    public void setSpeciesNumber(int speciesNumber) {
        this.speciesNumber = speciesNumber;
    }
    public void setRawName(String name) {
        this.name = name;
    }
    public void setTypeOne(String typeOne) {
        this.typeOne = typeOne;
    }
    public void setTypeTwo(String typeTwo) {
        this.typeTwo = typeTwo;
    }
    public void addAbility(Ability ability) { this.myAbilities.add(ability); }



    /*
     *
        Implementing the Parcelable Interface
    *
    */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(speciesNumber);
        out.writeString(name);
        out.writeString(typeOne);
        out.writeString(typeTwo);
        out.writeTypedList(myAbilities);

    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Pokemon> CREATOR = new Parcelable.Creator<Pokemon>() {
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Pokemon(Parcel in) {
        speciesNumber = in.readInt();
        name = in.readString();
        typeOne = in.readString();
        typeTwo = in.readString();
        in.readTypedList(myAbilities, Ability.CREATOR);
    }


    /*
     *
        Helper Methods
    *
    */

    private String pokedexNumberRepresentation(int number) {
        String repr = "#";
        if (number < 10) {
            repr = repr + "00" + number;
        } else if (number < 100) {
            repr = repr + "0" + number;
        } else {
            repr = repr + number;
        }
        return repr;
    }

    private String formatName(String na) {
        String name = "";

        if (id < 10000) {
            /* It's a regular, non-mega Pokemon */
            name = na.substring(0, 1).toUpperCase() + na.substring(1);
        } else {
            int dashIndex = -1;
            if (na.substring(na.length() - 2).equals("-x") || na.substring(na.length() - 2).equals("-y")) {
                int dashIndex2 = -1;
                for (int i = 0; i < na.length(); i++) {
                    if (na.charAt(i) == '-') {
                        if (dashIndex == -1) {
                            dashIndex = i;
                        }
                        else {
                            dashIndex2 = i;
                        }
                    }
                }

                name = na.substring(dashIndex + 1, dashIndex + 2).toUpperCase()
                        + na.substring(dashIndex + 2, dashIndex2)
                        + " "
                        + na.substring(0, 1).toUpperCase()
                        + na.substring(1, dashIndex)
                        + " "
                        + na.substring(dashIndex2 + 1).toUpperCase();
            } else {
                for (int i = 0; i < na.length(); i++) {
                    if (na.charAt(i) == '-') {
                        dashIndex = i;
                    }
                }
                name = na.substring(dashIndex + 1, dashIndex + 2).toUpperCase()
                        + na.substring(dashIndex + 2)
                        + " "
                        + na.substring(0, 1).toUpperCase()
                        + na.substring(1, dashIndex) ;
            }
        }


        return name;
    }



}
