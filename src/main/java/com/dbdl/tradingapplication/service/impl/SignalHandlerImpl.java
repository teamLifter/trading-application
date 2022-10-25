package com.dbdl.tradingapplication.service.impl;

import com.dbdl.tradingapplication.model.SignalSpecifications;
import com.dbdl.tradingapplication.service.SignalHandler;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class SignalHandlerImpl implements SignalHandler {

    private static final SignalSpecifications signalSpecs = new SignalSpecifications();

    @Override
    public void handleSignal(int signal) throws RuntimeException {
        Method signalSpec = getMethodBySignal(signal);
        executeSignalSpec(signalSpec);
    }

    private void executeSignalSpec(Method signalSpec) throws RuntimeException {
        if (signalSpec == null) {
            signalSpecs.signalSpec_default();
            signalSpecs.execute();
            return;
        }

        try {
            signalSpec.invoke(signalSpecs);
            signalSpecs.execute();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Method getMethodBySignal(int signal) {
        try {
            return signalSpecs.getClass().getDeclaredMethod(SignalSpecifications.SPEC_PREFIX + signal);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
