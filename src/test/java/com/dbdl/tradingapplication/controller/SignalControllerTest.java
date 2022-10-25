package com.dbdl.tradingapplication.controller;

import com.dbdl.tradingapplication.service.impl.SignalHandlerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class SignalControllerTest {

    /**
     * Class under Test.
     */
    @InjectMocks
    private SignalController cut;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(cut)
                .build();
    }

    @Mock
    private static SignalHandlerImpl signalHandler = new SignalHandlerImpl();

    @Test
    public void verifyExecuteSignal_InvocatoinFailed() throws Exception {
        int signal = 1;
        willThrow(new RuntimeException());

        MockHttpServletResponse response = mvc.perform(post("/signal/execute/{signal}", signal))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo(SignalController.ERROR_MESSAGE_PREFIX + signal);
    }

    @Test
    public void verifyExecuteSignal_Default() {
        int signal = -1;
        ResponseEntity<String> response = cut.executeSignal(signal);
        assertEquals(SignalController.SUCCESS_MESSAGE_PREFIX + signal, response.getBody());
    }

    @Test
    public void verifyExecuteSignal_1() {
        int signal = 1;
        ResponseEntity<String> response = cut.executeSignal(signal);
        assertEquals(SignalController.SUCCESS_MESSAGE_PREFIX + signal, response.getBody());
    }

    @Test
    public void verifyExecuteSignal_2() {
        int signal = 2;
        ResponseEntity<String> response = cut.executeSignal(signal);
        assertEquals(SignalController.SUCCESS_MESSAGE_PREFIX + signal, response.getBody());
    }

    @Test
    public void verifyExecuteSignal_3() {
        int signal = 3;
        ResponseEntity<String> response = cut.executeSignal(signal);
        assertEquals(SignalController.SUCCESS_MESSAGE_PREFIX + signal, response.getBody());
    }

    @Test
    public void verifyExecuteSignal_4() {
        int signal = 4;
        ResponseEntity<String> response = cut.executeSignal(signal);
        assertEquals(SignalController.SUCCESS_MESSAGE_PREFIX + signal, response.getBody());
    }

    @Test
    public void verifyExecuteSignal_5() {
        int signal = 4;
        ResponseEntity<String> response = cut.executeSignal(signal);
        assertEquals(SignalController.SUCCESS_MESSAGE_PREFIX + signal, response.getBody());
    }

}
