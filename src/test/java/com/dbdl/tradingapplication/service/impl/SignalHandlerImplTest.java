package com.dbdl.tradingapplication.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class SignalHandlerImplTest {

    /**
     * Class under Test.
     */
    private static SignalHandlerImpl cut = new SignalHandlerImpl();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void verifyHandleSignal_InvocationFail() {
        int signal = 1;
        SignalHandlerImpl mock = mock(SignalHandlerImpl.class);
        doThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> mock.handleSignal(signal));
    }

    @Test
    public void verifyHandleSignal_default() {
        int signal = -1;
        cut.handleSignal(signal);
        assertEquals("cancelTrades\n" +
                "doAlgo", outputStreamCaptor.toString().trim());
    }

    @Test
    public void verifyHandleSignal_1() {
        int signal = 1;
        cut.handleSignal(signal);
        assertEquals("setUp\n" +
                "setAlgoParam 1,60\n" +
                "performCalc\n" +
                "submitToMarket\n" +
                "doAlgo", outputStreamCaptor.toString().trim());
    }

    @Test
    public void verifyHandleSignal_2() {
        int signal = 2;
        cut.handleSignal(signal);
        assertEquals("reverse\n" +
                "setAlgoParam 1,80\n" +
                "submitToMarket\n" +
                "doAlgo", outputStreamCaptor.toString().trim());
    }

    @Test
    public void verifyHandleSignal_3() {
        int signal = 3;
        cut.handleSignal(signal);
        assertEquals("setAlgoParam 1,90\n" +
                "setAlgoParam 2,15\n" +
                "performCalc\n" +
                "submitToMarket\n" +
                "doAlgo", outputStreamCaptor.toString().trim());
    }
}
