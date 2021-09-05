package com.chandran.workflow.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
public class TaskMessage implements Serializable{

    private static final long serialVersionUID = 3860950636529458254L;

    private String taskId;
    private String taskName;
    private String approvalId;
    private Date startedAt;
    private Date createdAt;
    private Date completedAt;
    private String opportunityId;
    private String organizationId;
    private String transactionId;
    private String sfdcTaskId;
    private String status;
    private String workFlowType;
    private String workFlowProcess;
    private String priority;

   
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("taskId", taskId)
                .append("taskName", taskName)
                .append("approvalId", approvalId)
                .append("startTime", startedAt)
                .append("endTime", completedAt)
                .toString();
    }
}
