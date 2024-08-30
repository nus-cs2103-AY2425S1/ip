package lutodo.tasks;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{
    protected String startTime;
    protected LocalTime start;
    protected String endTime;
    protected LocalTime end;

    public EventTask(String wholeInfo) {
        super(splitInfo(wholeInfo)[0]);
        String[] info = splitInfo(wholeInfo);
        startTime = info[1].split("\\s+", 2)[1].trim();
        endTime = info[2].split("\\s+", 2)[1].trim();
        start = manageTime(startTime);
        end = manageTime(endTime);
    }

    public static String[] splitInfo(String wholeInfo) {
        return wholeInfo.split("/", 3);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + super.description +
                " /from: " + start.format(DateTimeFormatter.ofPattern("HH:mm")) +
                " /to: " + end.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
