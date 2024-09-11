package mediell.task;

import java.time.LocalDate;

/** Represents a Task with a Deadline. */
public class Deadline extends Task implements Comparable<Task> {
    private LocalDate by;

    public Deadline() {
        super("");
    }

    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
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

    public LocalDate getBy() {
        return by;
    }

    @Override
    public int compareTo(Task t) {
        if (t instanceof Event) {
            return by.compareTo(((Event) t).getFrom());
        } else if (t instanceof Deadline) {
            return by.compareTo(((Deadline) t).by);
        }
        return 1;
    }
}
