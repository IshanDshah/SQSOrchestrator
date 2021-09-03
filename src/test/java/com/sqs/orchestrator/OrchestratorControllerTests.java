package com.sqs.orchestrator;

import com.sqs.orchestrator.controller.MessageReceiverController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrchestratorControllerTests {

    @Mock
    private OrchestratorConfigurations orchConfig;
    @Mock
    private MessageReceiverController messageReceiverController;

    @Test
        void happyPath() throws IOException {
         when(messageReceiverController.messageSender("sd"));
        }


}
