package ned.tasks;

import java.util.regex.Pattern;

public abstract class Task {

    protected boolean isDone;
    protected String taskDescription;
    protected String taskType;

    protected Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    };

    public void markAsDone() {
        this.isDone = true;
    };

    public void markAsNotDone() {
        this.isDone = false;
    };

    public static int checkSizeOfInput(String[] strArr) {
        int count = 0;
        for (String s : strArr) {
            if (!s.isBlank())  {
                count += 1;
            }
        }
        return count;
    }
    @Override
    public String toString() {
        String marker = this.isDone ? "X" : " ";
        return String.format("[%s][%s] %s", this.taskType, marker, this.taskDescription);
    }

    public boolean isMatchingPattern(String searchPattern) {
        return Pattern.matches(searchPattern, this.taskDescription);
    }
    public abstract String toTextForm();

}
