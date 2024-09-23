package makima.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Task with a specified end date.
 */
public class Deadline extends Task {

    public static final int SAVE_PARAMETERS = 5;
    private LocalDateTime endTime;

    Deadline() {
        super();
    }

    /**
     * Instatiates a new deadline.
     *
     * @param name
     * @param endTime
     */
    public Deadline(String name, LocalDateTime endTime) {
        super(name);
        this.endTime = endTime;
    }

    /**
     * Instantiates a new deadline from a saved file.
     *
     * @param name
     * @param endTime
     * @param isDone
     */
    public Deadline(String name, LocalDateTime endTime, boolean isDone, PriorityLevel priorityLevel) {
        super(name, isDone, priorityLevel);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", endTime);
    }

    @Override
    public String toFileString() {
        return String.format("D\n%s%s\n", super.toFileString(), endTime);
    }

    @Override
    boolean load(ArrayList<String> data) {
        try {
            endTime = LocalDateTime.parse(data.get(3));
        } catch (DateTimeParseException e) {
            System.out.println("The file is corrupted! Delete it before restarting the program!");
            return false;
        }
        return super.load(data);
    }

    public static Deadline loadFromData(ArrayList<String> data) {
        Deadline deadline = new Deadline();
        if (deadline.load(data)) {
            return deadline;
        }
        return null;
    }
}
