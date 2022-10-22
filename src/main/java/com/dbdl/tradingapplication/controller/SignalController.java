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
    private SignalHandler signalHandler;

    public SignalController(SignalHandler signalHandler) {
        this.signalHandler = signalHandler;
    }

    @PostMapping("/execute/{signal}")
    public ResponseEntity<String> executeSignal(@PathVariable("signal") int signal) {
        signalHandler.handleSignal(signal);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Received signal: " + signal + ", Signal Specification has executed successfully.");
    }
}
