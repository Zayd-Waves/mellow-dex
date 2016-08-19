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

import me.zaydbille.pokedex.utils.MoveUtils;

public class Pokemon implements Comparable<Pokemon>, Parcelable {

    private int                             id;
    private String                          name;
    private int                             speciesNumber;
    private int                             height;
    private int                             weight;
    private int                             baseExperience;
    private int                             order;
    private String                          weightString;
    private String                          heightString;
    private String                          typeOne;
    private String                          typeTwo;
    private ArrayList<Ability>              myAbilities = new ArrayList<Ability>();
    private ArrayList<Move>                 myMoves = new ArrayList<>();
    private int                             baseHP;
    private int                             baseAttack;
    private int                             baseDefense;
    private int                             baseSpecialAttack;
    private int                             baseSpecialDefense;
    private int                             baseSpeed;
    private int                             baseTotal;
    private int                             spriteId;
    private int                             shinySpriteId;

    public boolean isCanEvolve() {
        return canEvolve;
    }
    public void setCanEvolve(boolean canEvolve) {
        this.canEvolve = canEvolve;
    }
    public String getEvolveMethod() {
        return evolveMethod;
    }
    public void setEvolveMethod(String evolveMethod) {
        this.evolveMethod = evolveMethod;
    }
    public String getEvolutionItem() {
        return evolutionItem;
    }
    public void setEvolutionItem(String evolutionItem) {
        this.evolutionItem = evolutionItem;
    }
    public String getEvolutionHeldItem() {
        return evolutionHeldItem;
    }
    public void setEvolutionHeldItem(String evolutionHeldItem) {
        this.evolutionHeldItem = evolutionHeldItem;
    }
    public int getEvolutionMinimumLevel() {
        return evolutionMinimumLevel;
    }
    public void setEvolutionMinimumLevel(int evolutionMinimumLevel) {
        this.evolutionMinimumLevel = evolutionMinimumLevel;
    }
    public String getEvolutionGender() {
        return evolutionGender;
    }
    public void setEvolutionGender(String evolutionGender) {
        this.evolutionGender = evolutionGender;
    }
    public String getEvolutionLocation() {
        return evolutionLocation;
    }
    public void setEvolutionLocation(String evolutionLocation) {
        this.evolutionLocation = evolutionLocation;
    }
    public String getEvolutionTimeOfDay() {
        return evolutionTimeOfDay;
    }
    public void setEvolutionTimeOfDay(String evolutionTimeOfDay) {
        this.evolutionTimeOfDay = evolutionTimeOfDay;
    }
    public String getEvolutionKnownMove() {
        return evolutionKnownMove;
    }
    public void setEvolutionKnownMove(String evolutionKnownMove) {
        this.evolutionKnownMove = evolutionKnownMove;
    }
    public String getEvolutionKnownMoveType() {
        return evolutionKnownMoveType;
    }
    public void setEvolutionKnownMoveType(String evolutionKnownMoveType) {
        this.evolutionKnownMoveType = evolutionKnownMoveType;
    }
    public int getEvolutionMinimumHappiness() {
        return evolutionMinimumHappiness;
    }
    public void setEvolutionMinimumHappiness(int evolutionMinimumHappiness) {
        this.evolutionMinimumHappiness = evolutionMinimumHappiness;
    }
    public int getEvolutionMinimumBeauty() {
        return evolutionMinimumBeauty;
    }
    public void setEvolutionMinimumBeauty(int evolutionMinimumBeauty) {
        this.evolutionMinimumBeauty = evolutionMinimumBeauty;
    }
    public int getEvolutionMinimumAffection() {
        return evolutionMinimumAffection;
    }
    public void setEvolutionMinimumAffection(int evolutionMinimumAffection) {
        this.evolutionMinimumAffection = evolutionMinimumAffection;
    }
    public int getEvolutionRelativePhysicalStats() {
        return evolutionRelativePhysicalStats;
    }
    public void setEvolutionRelativePhysicalStats(int evolutionRelativePhysicalStats) {
        this.evolutionRelativePhysicalStats = evolutionRelativePhysicalStats;
    }
    public int getEvolutionPartySpeciesId() {
        return evolutionPartySpeciesId;
    }
    public void setEvolutionPartySpeciesId(int evolutionPartySpeciesId) {
        this.evolutionPartySpeciesId = evolutionPartySpeciesId;
    }
    public int getEvolutionTradeSpeciesId() {
        return evolutionTradeSpeciesId;
    }
    public void setEvolutionTradeSpeciesId(int evolutionTradeSpeciesId) {
        this.evolutionTradeSpeciesId = evolutionTradeSpeciesId;
    }
    public boolean isNeedsOverworldRain() {
        return needsOverworldRain;
    }
    public void setNeedsOverworldRain(boolean needsOverworldRain) {
        this.needsOverworldRain = needsOverworldRain;
    }
    public boolean isNeedsUpsideDownSyste() {
        return needsUpsideDownSyste;
    }
    public void setNeedsUpsideDownSyste(boolean needsUpsideDownSyste) {
        this.needsUpsideDownSyste = needsUpsideDownSyste;
    }

    private boolean                         canEvolve;
    private String                          evolveMethod;
    private String                          evolutionItem;
    private String                          evolutionHeldItem;
    private int                             evolutionMinimumLevel;
    private String                          evolutionGender;
    private String                          evolutionLocation;
    private String                          evolutionTimeOfDay;
    private String                          evolutionKnownMove;
    private String                          evolutionKnownMoveType;
    private int                             evolutionMinimumHappiness;
    private int                             evolutionMinimumBeauty;
    private int                             evolutionMinimumAffection;
    private int                             evolutionRelativePhysicalStats;
    private int                             evolutionPartySpeciesId;
    private int                             evolutionTradeSpeciesId;
    private boolean                         needsOverworldRain;
    private boolean                         needsUpsideDownSyste;


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

    public void addMove(Move m) {
        myMoves.add(m);
    }
    public void addAllMoves(ArrayList<Move> moves) {
        this.myMoves = moves;
    }
    public void addAllAbilities(ArrayList<Ability> abilities) {
        this.myAbilities = abilities;
    }
    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }
    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }
    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }
    public void setBaseSpecialAttack(int baseSpecialAttack) {
        this.baseSpecialAttack = baseSpecialAttack;
    }
    public void setBaseSpecialDefense(int baseSpecialDefense) {
        this.baseSpecialDefense = baseSpecialDefense;
    }
    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }
    public void setSpriteId(int spriteId) {
        this.spriteId = spriteId;
    }
    public void setShinySpriteId(int shinySpriteId) {
        this.shinySpriteId = shinySpriteId;
    }
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
    public int getShinySpriteId() {
        return shinySpriteId;
    }
    public ArrayList<Move> getMyMoves() {
        return myMoves;
    }
    public int getBaseHP() {
        return baseHP;
    }
    public int getBaseDefense() {
        return baseDefense;
    }
    public int getBaseAttack() {
        return baseAttack;
    }
    public int getBaseSpecialAttack() {
        return baseSpecialAttack;
    }
    public int getBaseSpecialDefense() {
        return baseSpecialDefense;
    }
    public int getSpriteId() {
        return spriteId;
    }
    public int getBaseTotal() {
        return baseHP + baseAttack + baseDefense + baseSpecialAttack + baseSpecialDefense + baseSpeed;
    }
    public int getBaseSpeed() {
        return baseSpeed;
    }

    public ArrayList<Move> getLevelUpMoves(){
        ArrayList<Move> levelUpMoves = new ArrayList<>();
        for (int i = 0; i < myMoves.size(); i++) {
            if (myMoves.get(i).getLearnType().equals("level-up")) {
                levelUpMoves.add(myMoves.get(i));
            }
        }
        return MoveUtils.sortMovesByLevel(levelUpMoves);
    }
    public ArrayList<Move> getMachineMoves(){
        ArrayList<Move> machineMoves = new ArrayList<>();
        for (int i = 0; i < myMoves.size(); i++) {
            if (myMoves.get(i).getLearnType().equals("machine")) {
                machineMoves.add(myMoves.get(i));
            }
        }
        return machineMoves;
    }
    public ArrayList<Move> getEggMoves(){
        ArrayList<Move> eggMoves = new ArrayList<>();
        for (int i = 0; i < myMoves.size(); i++) {
            if (myMoves.get(i).getLearnType().equals("egg")) {
                eggMoves.add(myMoves.get(i));
            }
        }
        return eggMoves;
    }
    public ArrayList<Move> getTutorMoves(){
        ArrayList<Move> tutorMoves = new ArrayList<>();
        for (int i = 0; i < myMoves.size(); i++) {
            if (myMoves.get(i).getLearnType().equals("tutor")) {
                tutorMoves.add(myMoves.get(i));
            }
        }
        return tutorMoves;
    }

    /* Implementing the Parcelable Interface */

    /* 99.9% of the time you can just ignore this. */
    @Override
    public int describeContents() {
        return 0;
    }

    /* Write your object's data to the passed-in Parcel. */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(speciesNumber);
        out.writeString(name);
        out.writeString(typeOne);
        out.writeString(typeTwo);
        out.writeTypedList(myAbilities);

    }

    /*
        This is used to regenerate your object. All Parcelables must have a
        CREATOR that implements these two methods
     */
    public static final Parcelable.Creator<Pokemon> CREATOR = new Parcelable.Creator<Pokemon>() {
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    /* Example constructor that takes a Parcel and gives you an object populated with it's values. */
    private Pokemon(Parcel in) {
        speciesNumber = in.readInt();
        name = in.readString();
        typeOne = in.readString();
        typeTwo = in.readString();
        in.readTypedList(myAbilities, Ability.CREATOR);
    }


    /* Helper Methods */
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
