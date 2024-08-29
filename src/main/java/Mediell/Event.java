package Mediell;

import java.time.LocalDate;

/** Represents a Task as an Event. */
public class Event extends Task{
    private LocalDate from;
    private LocalDate to;

    public Event() {
        super("");
    }

    public Event(String taskName) {
        super(taskName.split("/from", 2)[0]);
        String temp = taskName.split("/from", 2)[1];
        String[] tempArray = temp.split("/to", 2);
        from = LocalDate.parse(tempArray[0].strip());
        to = LocalDate.parse(tempArray[1].strip());
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
