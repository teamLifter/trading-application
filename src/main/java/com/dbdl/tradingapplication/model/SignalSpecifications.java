package com.dbdl.tradingapplication.model;

import com.dbdl.tradingapplication.lib.algo.Algo;

public class SignalSpecifications {

    /**
     * Prefix of the methods of the signal specifications.
     */
    public static final String SPEC_PREFIX = "signalSpec_";

    private static final Algo algo = new Algo();

    public void execute() {
        algo.doAlgo();
    }

    public void signalSpec_default() {
        algo.cancelTrades();
    }

    public void signalSpec_1() {
        algo.setUp();
        algo.setAlgoParam(1, 60);
        algo.performCalc( );
        algo.submitToMarket();
    }

    public void signalSpec_2() {
        algo.reverse();
        algo.setAlgoParam(1, 80);
        algo.submitToMarket();
    }

    public void signalSpec_3() {
        algo.setAlgoParam(1, 90);
        algo.setAlgoParam(2, 15);
        algo.performCalc();
        algo.submitToMarket();
    }

    public void signalSpec_4() {
        algo.setUp();
        algo.setAlgoParam(3, 90);
        algo.performCalc();
        algo.submitToMarket();
    }

    public void signalSpec_5() {
        algo.reverse();
        algo.setAlgoParam(4, 120);
        algo.submitToMarket();
    }

    public void signalSpec_6() {
        algo.setAlgoParam(5, 111);
        algo.performCalc();
        algo.submitToMarket();
    }

}
