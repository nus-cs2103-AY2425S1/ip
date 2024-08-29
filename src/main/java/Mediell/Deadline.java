package Mediell;

import java.time.LocalDate;

/** Represents a Task with a Deadline. */
public class Deadline extends Task{
    private LocalDate by;

    public Deadline() {
        super("");
    }

    public Deadline(String taskName) {
        super(taskName.split("/by", 2)[0]);
        by = LocalDate.parse(taskName.split("/by", 2)[1].strip());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    /**
     * Checks if the provided string is a valid Deadline.
     * @param format String
     * @return true if it is a deadline else false
     */
    public static boolean isDeadlineFormat(String format) {
        return format.startsWith("D");
    }

    @Override
    public String taskToStorageFormat() {
        return "D|" + by + "|" + super.taskToStorageFormat();
    }

    @Override
    public void initStorageFormat(String format) {
        String[] temp = format.split("\\|", 3);
        by = LocalDate.parse(temp[1]);
        super.initStorageFormat(temp[2]);
    }
}
