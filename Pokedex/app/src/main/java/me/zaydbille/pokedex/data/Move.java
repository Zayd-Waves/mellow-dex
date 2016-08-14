/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          Move                                                |
|   Description:    Move entity class.                                  |
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

public class Move implements Comparable<Move> {

    private int                         id;
    private String                      name;
    private String                      type;
    private int                         category;
    private String                      description;
    private boolean                     HM;
    private Integer                     power;
    private Integer                     accuracy;
    private Integer                     effectChance;
    private Integer                     tmHmNumber;
    private Integer                     pp;
    private Integer                     level;
    private String                      powerString;
    private String                      accuracyString;
    private String                      probabilityString;
    private String                      tmHmString;
    private String                      ppString;
    private String                      learnType;
    private Integer                     priority;
    private Integer                     targetId;
    private Integer                     damageClass;

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
    public void setLevel(int lvl) { this.level = lvl; }
    public void setLearnType(String ltype) { this.learnType = ltype; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setTargetId(int target) { this.targetId = target; }
    public void setDamageClass(int dmgClass) { this.damageClass = dmgClass; }

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
                tmHmString = "HM 00" + (tmHmNumber - 100);
            } else {
                String prefix = "TM ";
                if (tmHmNumber > 99) {
                    prefix = "TM ";
                } else if(tmHmNumber > 9) {
                    prefix = "TM 0";
                } else {
                    prefix = "TM 00";
                }
                tmHmString = prefix + tmHmNumber;
            }
        }
        return tmHmString;
    }
    public String getPpString() {
        ppString = "" + pp;
        return ppString;
    }
    public boolean isHM() { return HM; }
    public Integer getLevel() { return level; }
    public String getLearnType() { return learnType; }
    public int getPriority() { return priority; }
    public int getTargetId() { return targetId; }
    public int getDamageClass() { return damageClass; }


    /* Implementing the Comparable Interface */
    public int compareTo(Move m)
    {
        return name.compareTo(m.getRawName());
    }

    /* Helper Methods */
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
