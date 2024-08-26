import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDateTime deadlineInfo;
    private static final DateTimeFormatter READ_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_PATTERN = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    Deadlines(String name, String details) {
        super(name);
        this.deadlineInfo = LocalDateTime.parse(details, READ_PATTERN);
    }

    @Override
    public String getWriteTaskInfo() {
        return this.deadlineInfo.format(READ_PATTERN);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + this.deadlineInfo.format(DISPLAY_PATTERN);
    }
}
