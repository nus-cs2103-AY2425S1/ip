package evelyn.task;

import java.time.LocalDate;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task{
    private LocalDate date = null;
    private String time = null;

    /**
     * Constructor of a Deadline task.
     * @param desccription Description of a Deadline task.
     * @param deadline Deadline of a Deadline task.
     * @param isMarked Status of a Deadline task.
     */
    public Deadline(String desccription, String deadline, boolean isMarked) {
        super(desccription, isMarked);
        if (deadline.contains(" ")) {
            String[] parts = deadline.split(" ");
            this.date = LocalDate.parse(parts[0]);
            this.time = parts[1];
        } else {
            this.date = LocalDate.parse(deadline);
        }
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() +
                (time == null? "" : " "+time) + ")";
    }
}
