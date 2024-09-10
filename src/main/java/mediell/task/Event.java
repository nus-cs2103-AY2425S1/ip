package mediell.task;

import java.time.LocalDate;

/** Represents a Task as an Event. */
public class Event extends Task{
    private LocalDate from;
    private LocalDate to;

    public Event() {
        super("");
    }

    public Event(String taskName, LocalDate from, LocalDate to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    /**
     * Checks if the provided string is a valid Event.
     * @param format String
     * @return true if it is an Event else false
     */
    public static boolean isEventFormat(String format) {
        return format.startsWith("E");
    }

    @Override
    public String taskToStorageFormat() {
        return "E|" + from + "|" + to + "|" + super.taskToStorageFormat();
    }

    @Override
    public void initStorageFormat(String format) {
        String[] temp = format.split("\\|", 4);
        from = LocalDate.parse(temp[1]);
        to = LocalDate.parse(temp[2]);
        super.initStorageFormat(temp[3]);
    }
}
