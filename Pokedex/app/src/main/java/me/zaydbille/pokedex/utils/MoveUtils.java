/*
 -----------------------------------------------------------------------
|                                                                       |
|   Class:          MoveUtils                                           |
|   Description:    MoveUtils class.                                    |
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
package me.zaydbille.pokedex.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import me.zaydbille.pokedex.data.Move;

public final class MoveUtils {

    public static ArrayList<Move> sortMovesByLevel(ArrayList<Move> moves) {
        ArrayList<Move> sortedMoves = moves;

        Collections.sort(sortedMoves, new Comparator<Move>() {
            public int compare(Move m1, Move m2) {
                return m1.getLevel() - m2.getLevel();
            }
        });

        return sortedMoves;
    }

    public static ArrayList<Move> sortByNumber(ArrayList<Move> moves) {
        ArrayList<Move> sortedMoves = moves;

        Collections.sort(sortedMoves, new Comparator<Move>() {
            public int compare(Move m1, Move m2) {
                return m1.getTmHmNumber() - m2.getTmHmNumber();
            }
        });

        return sortedMoves;
    }
}
