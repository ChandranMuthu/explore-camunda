package com.chandran.workflow.delegates;

import com.chandran.workflow.dto.TaskDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ApiService implements JavaDelegate {
    private static final Logger LOGGER = getLogger(ApiService.class);
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("Message received");
        LOGGER.info("Service call invoked");
        String currentTaskId = (String) delegateExecution.getProcessEngine().getRuntimeService().getVariable(delegateExecution.getProcessInstanceId(), "currentTaskId");
        String currentTaskName = (String) delegateExecution.getProcessEngine().getRuntimeService().getVariable(delegateExecution.getProcessInstanceId(), "currentTaskName");
        LOGGER.info(currentTaskId.toString());

        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(currentTaskId);
        taskDto.setTaskName(currentTaskName);
        taskDto.setApprovalId(RandomStringUtils.random(5));
        taskDto.setStartTime(new Date());
        LOGGER.info(taskDto.toString());
    }
}
