package com.domain;

import lombok.Data;

@Data
public class requestPut {
    private String id;
    private String sprintId;
    private String userId;
    private String priority;
    private String name;
    private String description;
    private String acceptanceCriteria;
    private int storyPoints;
    private int progress;
    private String startDate;
    private String dueDate;
    private String createDate;
    private int status;
}
