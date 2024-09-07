package makima.task;

import java.time.LocalDateTime;

/**
 * Task with a specified end date.
 */
public class Deadline extends Task {

    private LocalDateTime endTime;

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
    public Deadline(String name, LocalDateTime endTime, boolean isDone) {
        super(name, isDone);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", endTime);
    }

    public String toFileString() {
        return String.format("D\n%s%s\n", super.toFileString(), endTime);
    }
}
