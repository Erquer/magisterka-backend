package com.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class ProcessTask {
    @CsvBindByName
    String activityInstanceId;

    @CsvBindByName
    String assignee;

    @CsvBindByName
    @CsvDate("yyyy-M-d'T'H:mm:ss.SSSXXX")
    Date dueDate;

    @CsvBindByName
    String eventType;

    @CsvBindByName
    String name;

    @CsvBindByName
    String priority;

    @CsvBindByName
    String processDefinitionId;

    @CsvBindByName
    String processDefinitionKey;

    @CsvBindByName
    String processInstanceId;

    @CsvBindByName
    String taskDefinitionKey;

    @CsvBindByName
    String taskId;
    @CsvBindByName

    @CsvDate("yyyy-M-d'T'H:mm:ss.SSSXXX")
    Date timestamp;

    public String getActivityInstanceId() {
        return activityInstanceId;
    }

    public void setActivityInstanceId(String activityInstanceId) {
        this.activityInstanceId = activityInstanceId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ProcessTask{" +
                "activityInstanceId='" + activityInstanceId + '\'' +
                ", assignee='" + assignee + '\'' +
                ", dueDate=" + dueDate +
                ", eventType='" + eventType + '\'' +
                ", name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                ", processDefinitionId='" + processDefinitionId + '\'' +
                ", processDefinitionKey='" + processDefinitionKey + '\'' +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", taskDefinitionKey='" + taskDefinitionKey + '\'' +
                ", taskId='" + taskId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
