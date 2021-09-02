package com.chandran.workflow.delegates;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class StartTaskListener implements TaskListener {
    private static final Logger LOGGER = getLogger(StartTaskListener.class);

    private final ApiHelper apiHelper;

    @Autowired
    public StartTaskListener(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }


    @Override
    public void notify(final DelegateTask delegateTask) {


        Map<String, Object> variables = new HashMap<>();
        variables.put("currentTaskId", delegateTask.getId());
        variables.put("currentTaskName", delegateTask.getName());
        apiHelper.signalApiCall(delegateTask.getExecution().getBusinessKey(), variables);

        //delegateTask.getProcessEngine().getRuntimeService().correlateMessage("message_CallRestApi",);

    }


}
