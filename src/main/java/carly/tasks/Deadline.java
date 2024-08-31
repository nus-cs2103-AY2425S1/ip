package carly.tasks;

import java.text.MessageFormat;

/**
 * Represents a task of type Deadline.
 * A Deadline task includes a description and a due date.
 */
public class Deadline extends Task{
    /** The task type identifier for Deadline tasks.*/
    private static final String TASK_TYPE = "D";

    /** The due date of the task.*/
    private final String dueDate;

    /** Constructs a new Deadline task with the specified description and due date.*/
    public Deadline(String description, String duedate) {
        super(description);
        this.isDone = false;
        this.dueDate = duedate;
    }

    private String getDueDate(){
        return this.dueDate;
    }

    /**
     * Returns a string representation of the Deadline task, including
     * its type identifier, status, and due date.
     *
     * @return A string formatted as "[D][status] description (by: due date)".
     * @inheritDoc
     */
    @Override
    public String toString(){
        return MessageFormat.format("[{0}]{1} (by: {2})",
                TASK_TYPE,
                super.toString(),
                this.getDueDate());
    }
}
