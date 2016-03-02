package com.csbob.mahjongdiary.model;

/**
 * Score model
 * <p/>
 * Created by yiyang on 01/03/2016.
 */
public class Score {

    public String winner;
    public String loser;
    public int exp;

    public Score(String winner, String loser, int exp) {
        this.winner = winner;
        this.loser = loser;
        this.exp = exp;
    }
}
