package com.csbob.mahjongdiary.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Game domain model
 * <p/>
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
