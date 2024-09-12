package agave.Util;

import agave.Task.Task;
import agave.logic.Deadline;
import agave.logic.Event;
import agave.logic.ToDo;

import java.time.format.DateTimeParseException;

/**
 * Parses the user input and creates the corresponding task.
 */
public class Parser {
    private String userInput;

    /**
     * Constructs a Parser with the specified user input.
     *
     * @param userInput The user input to be parsed.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns the command from the user input.
     *
     * @return The command from the user input.
     */
    public String getCommand() {
        return userInput.split(" ")[0].toLowerCase();
    }

    /**
     * Returns the task number from the user input.
     *
     * @return The task number from the user input.
     * @throws AgaveException If the task number is not a valid integer.
     */
    public int getTaskNumber() throws AgaveException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            assert taskNumber > 0 : "Task number must be greater than 0";
            return taskNumber;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter a valid task number.");
        }
    }

    /**
     * Parses the user input and creates a todo task.
     *
     * @return The todo task created from the user input.
     * @throws AgaveException If the description of the todo is empty.
     */
    public Task parseTodo() throws AgaveException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new AgaveException("The description of a todo cannot be empty.");
        }
        return new ToDo(description);
    }

    /**
     * Parses the user input and creates a deadline task.
     *
     * @return The deadline task created from the user input.
     * @throws AgaveException If the description or deadline of the deadline is empty.
     */
    public Deadline parseDeadline() throws AgaveException {
        try {
            String[] split = userInput.split(" /by ");
            String description = split[0].substring(8).trim();
            String by = split[1].trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new AgaveException("The description and deadline of a task cannot be empty.");
            }
            return new Deadline(description, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter the deadline in the correct format: 'deadline <description> /by <yyyy/MM/dd HHmm>'.");
        } catch (DateTimeParseException e) {
            throw new AgaveException("Please enter the date and time in the correct format: 'yyyy/MM/dd HHmm'.");
        }
    }

    /**
     * Parses the user input and creates an event task.
     *
     * @return The event task created from the user input.
     * @throws AgaveException If the description or start time or end time of the event is empty.
     */
    public Event parseEvent() throws AgaveException {
        try {
            String[] split = userInput.split(" /from | /to ");
            String description = split[0].substring(5).trim();
            String from = split[1].trim();
            String to = split[2].trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new AgaveException("The description, start time, and end time of an event cannot be empty.");
            }
            return new Event(description, from, to);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter the event in the correct format: 'event <description> /from <yyyy/MM/dd HHmm> /to <yyyy/MM/dd HHmm>'.");
        } catch (DateTimeParseException e) {
            throw new AgaveException("Please enter the date and time in the correct format: 'yyyy/MM/dd HHmm'.");
        }
    }

    public String getKey() throws AgaveException {
        try {
            return userInput.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter a keyword to search for.");
        }
    }
}
