package Mediell;

import java.util.Objects;

public class Task {
    private String taskName;
    private boolean completed;
    public Task(String taskName) {
        this.taskName = taskName;
        completed = false;
    }

    public void markAsCompleted() {
        completed = true;
    }

    public void markAsUncompleted() {
        completed = false;
    }

    @Override
    public String toString() {
        String status = "";
        if (completed) {
            status = "[X] ";
        } else {
            status = "[ ] ";
        }
        return status + taskName;
    }

    public String taskToStorageFormat() {
        return (completed ? "1" : "0") + "|" + taskName;
    }

    public void initStorageFormat(String format) {
        String[] temp = format.split("\\|", 2);
        completed = Objects.equals(temp[0], "1");
        taskName = temp[1];
    }
}
