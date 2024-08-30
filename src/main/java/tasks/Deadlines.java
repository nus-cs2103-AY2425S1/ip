package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    private LocalDateTime deadline;

    public Deadlines(String name, String deadlineString) {
        super(name);
        this.deadline = LocalDateTime.parse(deadlineString, INPUT_FORMAT);
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getAdditionalInfo() {
        return deadline.format(OUTPUT_FORMAT);
    }

    @Override
    public String toFileString() {
        return getType() + "|" + getName() + "|" + isComplete() + "|" + getAdditionalInfo();
    }

    public String getDeadline() {
        String deadlineString = deadline.toString();
        String[] deadlineToSaveArray = deadlineString.split("T");

        return deadlineToSaveArray[0] + " " + deadlineToSaveArray[1];
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(" + deadline.format(OUTPUT_FORMAT) + ")";
    }
}
