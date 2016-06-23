package me.zaydbille.pokedex.data;

/**
 * Created by Zayd on 6/19/16.
 */
public class Team {


    private String title;
    private Pokemon[] pokemon = new Pokemon[6];
    private int count = 0;

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
