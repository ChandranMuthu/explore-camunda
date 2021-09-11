package com.chandran.workflow.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
public class TaskMessage implements Serializable{

    private static final long serialVersionUID = 3860950636529458254L;
    private String taskName;
    private String approvalId;
    private String status;
    private String priority;

   
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("taskName", taskName)
                .append("approvalId", approvalId)
                .append("status", status)
                .append("priority", priority)
                .toString();
    }
}
