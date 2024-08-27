package Tasks;

import java.time.format.DateTimeFormatter;
public abstract class Task {

//    protected static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("")
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
        ;
        return count;
    };
    @Override
    public String toString() {
        String marker = this.isDone ? "X" : " ";
        return String.format("[%s][%s] %s",this.taskType, marker, this.taskDescription);
    };
    public abstract String toTextForm();

}
