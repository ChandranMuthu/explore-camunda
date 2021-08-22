package com.chandran.workflow.delegates;

import com.chandran.workflow.dto.TaskDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

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
        Random random = new Random(50);

        //The if condition is added to test the retry mechanism
        int number = random.nextInt();
        if(number%2 != 0)  // If odd number, throw exception and retry mechanism will retry.
        {
            throw new IllegalStateException("Exception");
        }
        else
        {
            TaskDto taskDto = new TaskDto();
            taskDto.setTaskId(currentTaskId);
            taskDto.setTaskName(currentTaskName);
            taskDto.setApprovalId(RandomStringUtils.random(5));
            taskDto.setStartTime(new Date());
            LOGGER.info(taskDto.toString());
        }


    }
}
