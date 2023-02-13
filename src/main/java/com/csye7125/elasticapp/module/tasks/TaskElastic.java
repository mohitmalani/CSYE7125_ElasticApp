package com.csye7125.elasticapp.module.tasks;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "tasksindex")
public class TaskElastic {


    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TaskElastic{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
//                ", dueDate=" + dueDate +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
                ", priority='" + priority + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

//    public LocalDateTime getDueDate() {
//        return dueDate;
//    }
//
//    public void setDueDate(LocalDateTime dueDate) {
//        this.dueDate = dueDate;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "summary")
    private String summary;

//    @Field(type = FieldType.Date, name = "dueDate")
//    private LocalDateTime dueDate;
//
//    @Field(type = FieldType.Date, name = "createdAt")
//    private LocalDateTime createdAt;
//
//    @Field(type = FieldType.Date, name = "updatedAt")
//    private LocalDateTime updatedAt;

    @Field(type = FieldType.Text, name = "priority")
    private String priority;

    @Field(type = FieldType.Text, name = "state")
    private String state;

}
