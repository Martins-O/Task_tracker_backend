package com.saveyourtask.tasktracker.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskRequest {
    private String name;
    private String description;
    private String dueDateTime;
}
