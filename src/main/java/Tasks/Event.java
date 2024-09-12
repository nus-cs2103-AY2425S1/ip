package Tasks;

import Exceptions.DelphiException;
import Exceptions.EmptyInputException;
import Exceptions.InvalidInputException;
import Parser.Parser;

/**
 * Represents a Tasks.Event task with a specific time window.
 */
public class Event extends Task {
    private String window;

    /**
     * Constructs a Tasks.Event task with a given description.
     *
     * @param description The description of the Tasks.Event task.
     * @throws EmptyInputException if the description is empty.
     */
    public Event(String description, Parser p) throws DelphiException {
        // chatGPT was used here to help write this
        super(description);
        String[] formattedDeadline = p.parseEvent(description);
        this.name = formattedDeadline[0];
        window = "(" + formattedDeadline[1] + " " + formattedDeadline[2] + ")";
    }

    public void editTask(String newWindow, Parser p) throws DelphiException {
        String[] formattedDeadline = p.parseEvent(newWindow);
        window = "(" + formattedDeadline[1] + " " + formattedDeadline[2] + ")";
    }

    /**
     * Returns a string representation of the Tasks.Event task, including its time window.
     *
     * @return The string representation of the Tasks.Event task.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.name + " " + window;
    }
}
