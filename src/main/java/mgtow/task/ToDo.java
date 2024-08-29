package mgtow.task;

import mgtow.task.Task;

import java.time.LocalDate;

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc, "T");
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }
}