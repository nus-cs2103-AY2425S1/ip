import java.util.regex.Pattern;

public class Event extends Task {
    private String fromTiming;
    private String toTiming;

    private static String EventRegexWithoutTo = "^event (.+) /from (.+)";
    private static String EventRegexWithEmptyTo = "^event (.+) /from (.+) /to\\s";
    private static String EventRegexWithoutFrom = "^event (.+) /to (.+)";
    private static String EventRegexWithEmptyFrom = "^event (.+) /from\\s/to (.+)";

    private Event(String taskDescription, String fromTiming, String toTiming, boolean isDone) {
        super(taskDescription, isDone);
        this.fromTiming = fromTiming;
        this.toTiming = toTiming;
        this.taskType = "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.fromTiming, this.toTiming);
    }

    public static Event createEvent(String taskDescription, String eventFromDate, String eventToDate,
                                    boolean taskStatus) throws NedException {
        if (taskDescription.isBlank()) {
            throw new NedException("M'lord, this saved event task has no task description!");
        } else if (eventFromDate.isBlank()) {
            throw new NedException("M'lord, this saved event task has no from date!");
        } else if (eventToDate.isBlank()) {
            throw new NedException("M'lord, this saved event task has no to date!");
        }
        return new Event(taskDescription, eventFromDate, eventToDate, taskStatus);
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("event|%d|%s", status,this.taskDescription);
    }

}
