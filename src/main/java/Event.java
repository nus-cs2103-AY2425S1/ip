import java.util.Arrays;

public class Event extends Task{
    public Event() {
        super("", "E");
    }

    public void convertStringToTask(String[] slicedStr) {
        String[] task = Arrays.copyOfRange(slicedStr, 1, slicedStr.length);
        String taskDetail = String.join(" ", task);
        String[] eventTask = taskDetail.split("/");
        String startTime = eventTask[1].substring(5);
        String endTime = eventTask[2].substring(3);
        this.description = eventTask[0] + "(from: " + startTime + "to: " + endTime + ")";
    }
}

