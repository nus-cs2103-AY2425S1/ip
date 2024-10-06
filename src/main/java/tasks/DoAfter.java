package tasks;

import java.time.LocalDateTime;

public class DoAfter extends Task{
    protected LocalDateTime after;
    public DoAfter (String description, LocalDateTime after) {
        super(description);
        this.after = after;
    }

    /**
     * Returns the string representation of the do-after, including its status,
     * description, and due date.
     *
     * @return A formatted string representing the deadline.
     */
    @Override
    public String toString() {
        return "[DA]" + super.toString() + " (after: " + after.format(DISPLAY_FORMATTER) + ")";
    }

    /**
     * Returns the string format of the do-after task for saving to file.
     *
     * @return A formatted string representing the do-after task for saving purposes.
     */
    @Override
    public String toSaveFormat() {
        return "DA | " + super.toSaveFormat() + " | " + after.format(SAVE_FORMATTER);
    }
}
