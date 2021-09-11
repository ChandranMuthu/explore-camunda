package com.chandran.workflow.delegates;


import com.chandran.workflow.dto.TaskMessage;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class TriggerMessageEvent implements TaskListener {
    private static final Logger log = getLogger(TriggerMessageEvent.class);

    private final MessageApiService messageApiService;

    @Value("${sfdc-task-create-status}")
    private String sfTaskCreateStatus;

    @Value("${sfdc-task-complete-status}")
    private String sfTaskCompleteStatus;

    @Value("${sfdc-task-priority}")
    private String priority;

    @Autowired
    public TriggerMessageEvent(MessageApiService apiHelper) {
        this.messageApiService = apiHelper;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        log.debug("started method=notify ");
        String eventName = delegateTask.getEventName();

        // System.out.println("EventName" + eventName);
        log.info("EventName", eventName);

        TaskMessage taskMessage = new TaskMessage();

        if (ApplicationConstantUtil.TASK_EVENT_ASSIGNMENT.equalsIgnoreCase(eventName)) {
            log.debug("started method=createTaskEventMessage ");
            taskMessage = createTaskEventMessage(delegateTask);
            ObjectValue taskMessageDataValue = Variables.objectValue(taskMessage)
                    .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
                    .create();
            delegateTask.setVariable("taskMessage", taskMessageDataValue);
            VariableMap variableMap = Variables.createVariables().putValue("taskMessage", taskMessageDataValue);
            messageApiService.triggerMessageEvent(delegateTask.getExecution().getBusinessKey(), variableMap);
        } else if (ApplicationConstantUtil.TASK_EVENT_COMPLETE.equalsIgnoreCase(eventName)) {
            taskMessage = completeTaskEventMessage(delegateTask);
            ObjectValue taskMessageDataValue = Variables.objectValue(taskMessage)
                    .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
                    .create();
            VariableMap variableMap = Variables.createVariables().putValue("taskMessage", taskMessageDataValue);
            messageApiService.triggerMessageEvent(delegateTask.getExecution().getBusinessKey(), variableMap);
            log.debug("started method=completeTaskEventMessage ");
        } else {
            log.error("NO_MATCHING_TASK_EVENT_TYPE: method=TriggerMessageEvent : notify");
        }

    }

    private TaskMessage createTaskEventMessage(DelegateTask delegateTask) {
        log.debug("started method=createTaskEventMessage ");
        TaskMessage response = new TaskMessage();
        response.setStatus(sfTaskCreateStatus);
        String approvalId = (String) delegateTask.getExecution().getVariable("approvalId");
        response.setApprovalId(approvalId);
        response.setTaskName(delegateTask.getName());
        response.setPriority(priority);
        log.debug("finished method=createTaskEventMessage ");
        return response;
    }

    private TaskMessage completeTaskEventMessage(DelegateTask delegateTask) {
        log.debug("started method=completeTaskEventMessage ");
        TaskMessage response = (TaskMessage) delegateTask.getExecution().getVariable(ApplicationConstantUtil.TASK_EVENT_MESSAGE_NAME);
        response.setStatus(sfTaskCompleteStatus);
        response.setPriority(priority);
        log.debug("finished method=completeTaskEventMessage ");
        return response;
    }
}
