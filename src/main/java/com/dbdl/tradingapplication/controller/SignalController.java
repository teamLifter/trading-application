package com.dbdl.tradingapplication.controller;

import com.dbdl.tradingapplication.service.SignalHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signal")
public class SignalController {
    public static final String SUCCESS_MESSAGE_PREFIX = "Signal Specification has executed successfully for the signal - ";
    public static final String ERROR_MESSAGE_PREFIX = "Invoking Signal Specification has failed for the signal - ";
    private SignalHandler signalHandler;

    public SignalController(SignalHandler signalHandler) {
        this.signalHandler = signalHandler;
    }

    @PostMapping("/execute/{signal}")
    public ResponseEntity<String> executeSignal(@PathVariable("signal") int signal) {
        try {
            signalHandler.handleSignal(signal);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(SUCCESS_MESSAGE_PREFIX + signal);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ERROR_MESSAGE_PREFIX + signal);
        }
    }
}
