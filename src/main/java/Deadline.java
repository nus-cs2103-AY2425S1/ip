import java.util.Arrays;

public class Deadline extends Task{
    protected String dueTime;
    public Deadline() {
        super("", "D");
    }

    public void convertStringToTask(String[] slicedStr) {
        String[] task = Arrays.copyOfRange(slicedStr, 1, slicedStr.length);
        String deadlineTaskDetail = String.join(" ", task);
        String[] deadlineTask = deadlineTaskDetail.split(" /");
        this.description = deadlineTask[0];
        this.dueTime = deadlineTask[1].substring(3);
    }

    public void convertSavedDataToTask(String[] dataArr) {
        this.setMarkStatus(dataArr[1].equals("1"));
        this.description = dataArr[2];
        this.dueTime = dataArr[3];
    }

    @Override
    public String toString() {
        return this.description + " (by: " + this.dueTime + ")";
    }
}
