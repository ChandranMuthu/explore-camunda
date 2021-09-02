package com.chandran.workflow.delegates;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CompleteTaskListener implements TaskListener {
    private static final Logger LOGGER = getLogger(CompleteTaskListener.class);

    private final ApiHelper apiHelper;

    public CompleteTaskListener(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("currentTaskId", delegateTask.getId());
        variables.put("currentTaskName", delegateTask.getName());
        apiHelper.signalApiCall(delegateTask.getExecution().getBusinessKey(), variables);
        LOGGER.info("Message triggered");
    }
}
