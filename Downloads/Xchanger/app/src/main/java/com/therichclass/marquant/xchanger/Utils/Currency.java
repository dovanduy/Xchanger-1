package com.therichclass.marquant.xchanger.Utils;

/**
 * Created by marquant on 11/2/2017.
 */

public class Currency {
    private String name;
    private double ethrate;
    private double btcrate;

    public Currency(String name, double ethrate, double btcrate) {
        this.name = name;
        this.ethrate = ethrate;
        this.btcrate = btcrate;
    }

    public String getName() {
        return name;
    }

    public double getEthrate() {
        return ethrate;
    }


    public double getBtcrate() {
        return btcrate;
    }
}
