/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          Team                                                |
|   Description:    Team class that represents a Pokemon team entity.   |
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

public class Team {

    private String                          title;
    private Pokemon[]                       pokemon = new Pokemon[6];
    private int                             count = 0;

    public Team() {
    }

    public String getTitle() {
        return title;
    }
    public Pokemon[] getPokemon() {
        return pokemon;
    }
    public int getCount() {
        return count;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setPokemon(Pokemon[] pokemon) {
        this.pokemon = pokemon;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
