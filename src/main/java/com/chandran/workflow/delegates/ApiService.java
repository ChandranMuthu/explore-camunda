package com.chandran.workflow.delegates;

import com.chandran.workflow.dto.TaskMessage;
import com.chandran.workflow.exception.RestApiException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ApiService implements JavaDelegate {
    private static final Logger LOGGER = getLogger(ApiService.class);
    private final RestTemplate restTemplate;
    @Value("${task-message-publish-api}")
    private String apiUrl;

    public ApiService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("Message received");
        LOGGER.info("Service call invoked");
        TaskMessage taskMessage = (TaskMessage) delegateExecution.getVariable("taskMessage");
        LOGGER.info(taskMessage.toString());

        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(apiUrl, taskMessage, Object.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            LOGGER.info("Task message posted successfully for the approvalId - {} ", taskMessage.getApprovalId());
            LOGGER.info("Response from the API - {} ", responseEntity.getBody().toString());
        } else {
            LOGGER.error("Task message posting failed with error {} ", responseEntity.getBody().toString());
            throw new RestApiException("Error occurred while posting the task message with approvalId - " + taskMessage.getApprovalId());
        }
    }
}
