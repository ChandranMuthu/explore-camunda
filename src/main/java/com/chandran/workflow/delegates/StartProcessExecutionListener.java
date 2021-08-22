package com.chandran.workflow.delegates;

import com.chandran.workflow.configuration.ApplicationProperties;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartProcessExecutionListener implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("assignee", applicationProperties.getAssignee());
    }

    private final ApplicationProperties applicationProperties;

    @Autowired
    public StartProcessExecutionListener(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
}
