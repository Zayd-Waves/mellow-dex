/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          Ability                                             |
|   Description:    Ability entity class.                               |
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

public class Ability implements Comparable<Ability>, Parcelable {

    private int                             abilityId;
    private String                          name;
    private String                          description;

    /*
        Ability "Type"
        0 - Primary Ability
        1 - Hidden Ability
     */
    private int                             type;

    public Ability() {
    }

    public void setAbilityId(int id) { this.abilityId = id; }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(int type) { this.type = type; }

    public int getAbilityId() { return abilityId; }
    public String getRawName() { return name; }
    public String getName() {
        return formatName(name);
    }
    public String getDescription() {
        return description;
    }
    public int getType() { return type; }

    /* Implementing the Comparable Interface */
    public int compareTo(Ability a)
    {
        return name.compareTo(a.getRawName());
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
        out.writeString(name);
        out.writeString(description);
        out.writeInt(type);
    }

    /*
        This is used to regenerate your object. All Parcelables must have a CREATOR
        that implements these two methods.
     */
    public static final Parcelable.Creator<Ability> CREATOR = new Parcelable.Creator<Ability>() {
        public Ability createFromParcel(Parcel in) {
            return new Ability(in);
        }

        public Ability[] newArray(int size) {
            return new Ability[size];
        }
    };

    /* Example constructor that takes a Parcel and gives you an object populated with it's values. */
    private Ability(Parcel in) {
        name = in.readString();
        description = in.readString();
        type = in.readInt();
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
