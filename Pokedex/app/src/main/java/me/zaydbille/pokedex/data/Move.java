package me.zaydbille.pokedex.data;

/**
 * Created by Zayd on 6/04/16.
 */
public class Move implements Comparable<Move> {


    private int     id;
    private String  name;
    private String  type;
    private int     category;
    private String  description;
    private boolean HM;
    private Integer power;
    private Integer accuracy;
    private Integer effectChance;
    private Integer tmHmNumber;
    private Integer pp;

    private String powerString;
    private String accuracyString;
    private String probabilityString;
    private String tmHmString;
    private String ppString;

    public Move() {

    }

    public void setId(int id) { this.id = id; }
    public void setRawName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setCategory(int category) {
        this.category = category;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
    public void setPp(int pp) {
        this.pp = pp;
    }
    public void setTmHmNumber(int tmHmNumber) {
        this.tmHmNumber = tmHmNumber;
    }
    public void setEffectChance(int effectChance) {
        this.effectChance = effectChance;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setHM(boolean hm) { this.HM = hm; }

    public int getId() { return id; }
    public String getRawName() {
        return name;
    }
    public String getName() { return formatName(name); }
    public String getType() {
        return type;
    }
    public int getCategory() {
        return category;
    }
    public Integer getPower() {
        return power;
    }
    public Integer getAccuracy() {
        return accuracy;
    }
    public Integer getPp() {
        return pp;
    }
    public Integer getTmHmNumber() {
        return tmHmNumber;
    }
    public Integer getEffectChance() {
        return effectChance;
    }
    public String getDescription() {
        return description;
    }
    public String getPowerString() {
        powerString = "" + power;
        return powerString;
    }
    public String getAccuracyString() {
        if (accuracy > 100) {
            accuracyString = "∞";
        } else {
            accuracyString = accuracy + "";
        }
        return accuracyString;
    }
    public String getProbabilityString() {
        probabilityString = effectChance + "%";
        return probabilityString;
    }
    public String getTmHmString() {
        if (tmHmNumber == null) {
            tmHmString = "--";
        } else {
            if (isHM()) {
                tmHmString = "HM " + tmHmNumber;
            } else {
                tmHmString = "TM " + tmHmNumber;
            }
        }
        return tmHmString;
    }
    public String getPpString() {
        ppString = "" + pp;
        return ppString;
    }
    public boolean isHM() { return HM; }


    /*
     *
        Implementing the Comparable Interface
    *
    */
    public int compareTo(Move m)
    {
        return name.compareTo(m.getRawName());
    }




    /*
     *
        Helper Methods
    *
    */

    private String formatName(String na) {
        String name = "";
        name += na.substring(0, 1).toUpperCase();

        for (int i = 1; i < na.length(); i++) {
            if (na.charAt(i) == '-') {
                name += " ";
                name += na.substring(i + 1, i + 2).toUpperCase();
                i += 1;
            } else {
                name += na.charAt(i);
            }

        }

        return name;
    }

}
