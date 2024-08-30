package lutodo.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    protected String ddl;
    protected LocalDate deadLine;

    public DeadlineTask(String wholeInfo) {
        super(splitInfo(wholeInfo)[0]);
        String[] info = splitInfo(wholeInfo);
        ddl = info[1].split("\\s+", 2)[1];
        deadLine = manageDate(ddl);
    }

    public static String[] splitInfo(String wholeInfo) {
        return wholeInfo.split("/", 2);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + super.description + " /by: " +
                deadLine.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
