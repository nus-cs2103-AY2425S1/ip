import java.util.Arrays;

public class Event extends Task {

    protected String startTime;
    protected String endTime;
    public Event() {
        super("", "E");
    }

    public void convertStringToTask(String[] slicedStr) {
        String[] task = Arrays.copyOfRange(slicedStr, 1, slicedStr.length);
        String taskDetail = String.join(" ", task);
        String[] eventTask = taskDetail.split(" /");
        this.description = eventTask[0];
        this.startTime = eventTask[1].substring(5);
        this.endTime = eventTask[2].substring(3);
    }

    public void convertSavedDataToTask(String[] dataArr) {
        this.setMarkStatus(dataArr[1].equals("1"));
        this.description = dataArr[2];
        this.startTime = dataArr[3];
        this.endTime = dataArr[4];
    }

    @Override
    public String toString() {
        return this.description + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}

