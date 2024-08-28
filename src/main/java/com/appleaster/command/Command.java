package com.appleaster.command;
import java.time.LocalDate;

import com.appleaster.task.Task;

public class Command {
    private final CommandType type;
    private Task task;
    private int taskIndex;
    private LocalDate date;

    public Command(CommandType type) {
        this.type = type;
    }

    public Command(CommandType type, Task task) {
        this.type = type;
        this.task = task;
    }

    public Command(CommandType type, int taskIndex) {
        this.type = type;
        this.taskIndex = taskIndex;
    }

    public Command(CommandType type, LocalDate date) {
        this.type = type;
        this.date = date;
    }

    public CommandType getType() {
        return type;
    }

    public Task getTask() {
        return task;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public LocalDate getDate() {
        return date;
    }
}