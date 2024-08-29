package lexi.task;
import java.time.format.DateTimeFormatter;

public abstract class DatedTask extends Task {
    public static DateTimeFormatter outputFormatter
            = DateTimeFormatter.ofPattern("d MMM yyy, HH:mm");
    public DatedTask(String taskName) {
        super(taskName);
    }

}
