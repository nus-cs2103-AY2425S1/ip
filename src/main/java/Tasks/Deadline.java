package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
<<<<<<< HEAD
    private final LocalDateTime endTime; // The end time of a deadline task
=======
    private final LocalDateTime endTime;
>>>>>>> branch-A-CodingStandard

    /**
     * Initialisation of a Deadline task with the required attributes.
     * @param name Name of the task.
     * @param by End date/ time of the task.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.endTime = by;

    }

    /**
     * Returns a string on how a Deadline should be stored in a save file.
     * @return Save format for the task.
     */
    @Override
    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        if (this.getCompleted()) {
            saveTaskInfo.append("done, ");
        }
        else {
            saveTaskInfo.append("undone, ");
        }
        saveTaskInfo.append("deadline ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append(" /by ");
        saveTaskInfo.append(this.endTime.format(formatter));
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    /**
     * Returns the string representation of a Deadline.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String date = this.endTime.format(formatter);
        return "[D] " + super.toString() + " (by: " + date + ")";
    }
}
