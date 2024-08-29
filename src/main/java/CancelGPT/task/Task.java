package CancelGPT.task;

import CancelGPT.exception.task.InvalidTask;

import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getStatusInt() {
        return (isDone ? 1 : 0);
    }

    public static boolean getStatusBoolean(int statusInt) {
        return (statusInt == 1);
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon()
                + "] " + this.getDescription();
    }

    public String getSavedDataString() {
        return this.getStatusInt() + " | " + this.getDescription();
    }

    public static Task getTaskFromSavedDataString(String savedDataString) throws InvalidTask {
        String[] savedDataStringArr = savedDataString.split("\\s*\\|\\s*");
        try {
            if (savedDataStringArr[0].equals("T")) {
                return ToDo.getTaskFromSavedDataStringArr(savedDataStringArr);
            }
            if (savedDataStringArr[0].equals("D")) {
                return Deadline.getTaskFromSavedDataStringArr(savedDataStringArr);
            }
            if (savedDataStringArr[0].equals("E")) {
                return Event.getTaskFromSavedDataStringArr(savedDataStringArr);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTask("Unable to read task from tasks storage due to incorrect format saved");
        }
        return null;
    }
}
