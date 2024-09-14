package task;

import exceptions.DelphiException;
import parser.Parser;

/**
 * Represents a task.Deadline task with a specific deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * constructor for a new deadline object
     *
     * @param description the task description
     * @param p a parser object to help reformat the deadline
     * @throws DelphiException if input is not correctly formatted
     */
    public Deadline(String description, Parser p) throws DelphiException {
        super(description);
        String[] formattedDeadline = p.parseDeadline(description);
        this.name = formattedDeadline[0];
        this.deadline = formattedDeadline[1];
    }

    /**
     * provides a way for the user to edit the deadlines of existing tasks by passing in a new deadline
     *
     * @param newDeadline string representation of the deadline
     * @param p the parser used to help parse the new deadline
     */
    public void editTask(String newDeadline, Parser p) throws DelphiException {
        String[] formattedDeadline = p.parseDeadline(newDeadline);
        deadline = formattedDeadline[1];
    }

    /**
     * Returns a string representation of the task.Deadline task, including its deadline.
     *
     * @return The string representation of the task.Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.name + " " + deadline;
    }
}
