package com.juaracoding.drivers.strategies;

import com.juaracoding.utils.Constants;

import java.sql.Driver;


public class DriverStrategyImplementer {

    public static DriverStrategy chooseStrategy(String strategy){

        switch (strategy) {
            case Constants.CHROME:
                return new Chrome();
            case Constants.FIREFOX:
                return new Firefox();
            default:
                return null;

        }


    }
}
