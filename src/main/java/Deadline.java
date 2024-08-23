import java.util.Arrays;

public class Deadline extends Task{
    public Deadline() {
        super("", "D");
    }

    public void convertStringToTask(String[] slicedStr) {
        String[] task = Arrays.copyOfRange(slicedStr, 1, slicedStr.length);
        String deadlineTaskDetail = String.join(" ", task);
        String[] deadlineTask = deadlineTaskDetail.split("/");
        this.description = deadlineTask[0] + "(by: " + deadlineTask[1].substring(3) + ")";
    }
}
