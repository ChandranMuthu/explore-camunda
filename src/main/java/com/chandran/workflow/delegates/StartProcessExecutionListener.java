package com.chandran.workflow.delegates;

import com.chandran.workflow.configuration.ApplicationProperties;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartProcessExecutionListener implements ExecutionListener {

    private final ApplicationProperties applicationProperties;

    @Autowired
    public StartProcessExecutionListener(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
    
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("assignee", applicationProperties.getAssignee());
    }
}
