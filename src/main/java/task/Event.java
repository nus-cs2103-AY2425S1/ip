package task;

import exceptions.DelphiException;
import exceptions.EmptyInputException;
import parser.Parser;

/**
 * Represents a task.Event task with a specific time window.
 */
public class Event extends Task {
    private String window;

    /**
     * Constructs a task.Event task with a given description.
     *
     * @param info an array containing the task description, start time and end time
     * @throws EmptyInputException if the description is empty.
     */
    public Event(String[] info) throws DelphiException {
        super(info[0]);
        window = "(" + info[1] + " " + info[2] + ")";
    }

    /**
     * provides a way for the user to edit the timeframe of existing events by passing in a new window
     *
     * @param newWindow string representation of the deadline
     * @param p the parser used to help parse the new deadline
     */
    public void editTask(String newWindow, Parser p) throws DelphiException {
        String[] formattedDeadline = p.parseEvent(newWindow);
        window = "(" + formattedDeadline[1] + " " + formattedDeadline[2] + ")";
    }

    /**
     * Returns a string representation of the task.Event task, including its time window.
     *
     * @return The string representation of the task.Event task.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.name + " " + window;
    }
}
