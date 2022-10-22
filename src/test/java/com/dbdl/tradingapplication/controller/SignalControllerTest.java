package com.dbdl.tradingapplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class SignalControllerTest {

    @Autowired
    private SignalController signalController;

    @Test
    public void verifyExecuteSignal() {
        int signal = 1;
        ResponseEntity<String> stringResponseEntity = signalController.executeSignal(signal);
        //TODO replace with Assert verification
        System.out.println(stringResponseEntity.getBody());
    }
}
