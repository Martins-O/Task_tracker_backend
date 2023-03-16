package com.saveyourtask.tasktracker.data.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TaskDto {
    private int code;
    private String message;
    private long id;
    private boolean isSuccess;
}
