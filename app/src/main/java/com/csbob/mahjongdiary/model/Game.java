package com.csbob.mahjongdiary.model;

import android.util.Log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Game domain model
 * <p>
 * Created by yiyang on 01/03/2016.
 */
public class Game implements Serializable {

    public String east;
    public String west;
    public String south;
    public String north;

    public BigDecimal unitPay;
    public int maxExp;

    public boolean isSinglePay;
    public Date playDate;

    public List<Score> scores = new ArrayList<>();
    public BigDecimal eastResult = BigDecimal.ZERO;
    public BigDecimal westResult = BigDecimal.ZERO;
    public BigDecimal southResult = BigDecimal.ZERO;
    public BigDecimal northResult = BigDecimal.ZERO;

    public Game(String east, String west, String south, String north, BigDecimal unitPay, int maxExp, boolean isSinglePay) {
        this.east = east;
        this.west = west;
        this.south = south;
        this.north = north;
        this.unitPay = unitPay;
        this.maxExp = maxExp;
        this.isSinglePay = isSinglePay;
        this.playDate = new Date();
    }

    public void addScore(Score score) {
        this.scores.add(score);
        String winner = score.winner;
        String loser = score.loser;
        int exp = score.exp;
        applyScore(winner, loser, exp, false);
    }

    private void applyScore(String winner, String loser, int exp, boolean isDelete) {
        BigDecimal two = new BigDecimal("2");
        BigDecimal delta = two.pow(exp - 1).multiply(unitPay);
        BigDecimal profit;
        BigDecimal loss;

        if (isDelete) delta = delta.negate();
        if (isSinglePay) {
            profit = loss = delta;
        } else {
            loss = delta.divide(two);
            Log.i("DEBUG", "" + loss);
            profit = delta.multiply(two).add(loss);
        }

        boolean self = (!loser.equalsIgnoreCase(east)
                && !loser.equalsIgnoreCase(west)
                && !loser.equalsIgnoreCase(south)
                && !loser.equalsIgnoreCase(north));

        if (self) profit = delta.multiply(new BigDecimal("4"));

        // get winner right
        if (winner.equalsIgnoreCase(east)) eastResult = eastResult.add(profit);
        if (winner.equalsIgnoreCase(west)) westResult = westResult.add(profit);
        if (winner.equalsIgnoreCase(south)) southResult = southResult.add(profit);
        if (winner.equalsIgnoreCase(north)) northResult = northResult.add(profit);

        // get loser right
        if (self) {
            eastResult = eastResult.subtract(delta);
            westResult = westResult.subtract(delta);
            southResult = southResult.subtract(delta);
            northResult = northResult.subtract(delta);
        } else {
            if (isSinglePay) {
                if (loser.equalsIgnoreCase(east)) eastResult = eastResult.subtract(loss);
                if (loser.equalsIgnoreCase(west)) westResult = westResult.subtract(loss);
                if (loser.equalsIgnoreCase(south)) southResult = southResult.subtract(loss);
                if (loser.equalsIgnoreCase(north)) northResult = northResult.subtract(loss);
            } else {
                eastResult = loser.equalsIgnoreCase(east) ? eastResult.subtract(delta) : eastResult.subtract(loss);
                westResult = loser.equalsIgnoreCase(west) ? westResult.subtract(delta) : westResult.subtract(loss);
                southResult = loser.equalsIgnoreCase(south) ? southResult.subtract(delta) : southResult.subtract(loss);
                northResult = loser.equalsIgnoreCase(north) ? northResult.subtract(delta) : northResult.subtract(loss);
            }
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "east='" + east + '\'' +
                ", west='" + west + '\'' +
                ", south='" + south + '\'' +
                ", north='" + north + '\'' +
                ", unitPay=" + unitPay +
                ", maxExp=" + maxExp +
                ", isSinglePay=" + isSinglePay +
                ", playDate=" + playDate +
                '}';
    }
}
