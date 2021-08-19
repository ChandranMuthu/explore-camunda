package com.chandran.workflow.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class TaskDto {
    private String taskId;
    private String taskName;
    private String approvalId;
    private Date startTime;
    private Date endTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("taskId", taskId)
                .append("taskName", taskName)
                .append("approvalId", approvalId)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .toString();
    }
}
