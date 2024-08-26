import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String type;
    private LocalDateTime dateTime;

    public Deadline(String desc, String type, LocalDateTime dateTime, boolean isDone) {
        super(desc, isDone);
        this.type = type;
        this.dateTime = dateTime;
    }

    private String datetimeToString(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return this.dateTime.format(formatter);
    }

    @Override
    public String taskToString() {
        return this.type + "::" + super.getStatus() + "::" + super.getDesc() + "::" + datetimeToString("yyyy-MM-dd HHmm") + "\n";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + "by: " + datetimeToString("dd MMM yyyy HH:mm") + ")";
    }
}

