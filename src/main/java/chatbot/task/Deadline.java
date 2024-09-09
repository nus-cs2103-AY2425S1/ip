package chatbot.task;

import java.time.LocalDateTime;

/**
 * Represents the concept of an Event task that the user adds into his todolist
 * The Event class extends the abstract class of Task
 */
public class Deadline extends Task {
    /** Deadline of task */
    private LocalDateTime deadline;

    /**
     * Overloaded constructor for the Deadline task, taking in 3 arguments
     *
     * @param name Name of Deadline task
     * @param deadline Date and time of the deadline
     * @param isDone Boolean value representing whether the task has been marked as completed
     */
    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        super(name, isDone);

        assert deadline != null : "deadline should not be null";

        this.deadline = deadline;
    }

    /**
     * Overloaded constructor for the Deadline task, taking in 2 arguments
     *
     * @param name Name of Deadline task
     * @param deadline Date and time of the deadline
     */
    public Deadline(String name, LocalDateTime deadline) {
        this(name, deadline, false);
    }

    /**
     * Overridden toString method, displaying the type of the task along with other details
     *
     * @return String representation of the Deadline task
     */
    @Override
    public String toString() {
        String str = String.format("[D]%s (by: %s)", super.toString(),
                Task.formatDate(this.deadline));
        return str;
    }

    /**
     * Encodes the Deadline task into a string, to be written to a text file
     *
     * @return String encoding of the Deadline task
     */
    @Override
    public String encode() {
        return "D|" + super.encode() + "|" + this.deadline;
    }
}
