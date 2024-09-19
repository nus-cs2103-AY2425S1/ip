package darkpool.parser.validator;

import java.time.format.DateTimeParseException;
import java.util.Objects;

import darkpool.DarkpoolException;
import darkpool.task.Event;

/**
 * ValidateEvent class is a class that contains methods to validate the user input for event tasks.
 * It contains methods to validate the user input, and to create an event task.
 */
public class ValidateEvent {

    /**
     * validate method is a method that validates the user input for event tasks.
     * It checks if the user input is in the correct format.
     * @param userInput the user input to be validated
     * @throws DarkpoolException if the user input is in the wrong format
     */
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("the event syntax is [task_desc] /from [from_date] /to [to_date]");
        }
    }

    /**
     * validateTo method is a method that validates the 'to' part of the user input for event tasks.
     * It checks if the 'to' part is in the correct format.
     * @param toParts the 'to' part of the user input to be validated
     * @throws DarkpoolException if the 'to' part is in the wrong format
     */
    public static void validateTo(String[] toParts) throws DarkpoolException {
        if (toParts.length > 3) {
            throw new DarkpoolException("only one to pls");
        } else if (toParts.length < 2 || toParts[1].trim().isEmpty()) {
            throw new DarkpoolException("to where???");
        } else if (toParts[0].trim().isEmpty()) {
            throw new DarkpoolException("from where???");
        }
    }

    /**
     * validateFrom method is a method that validates the 'from' part of the user input for event tasks.
     * It checks if the 'from' part is in the correct format.
     * @param fromParts the 'from' part of the user input to be validated
     * @throws DarkpoolException if the 'from' part is in the wrong format
     */
    public static void validateFrom(String[] fromParts) throws DarkpoolException {
        if (fromParts.length > 2) {
            throw new DarkpoolException("only one from pls");
        } else if (fromParts.length < 2 || fromParts[1].trim().isEmpty()) {
            throw new DarkpoolException("where is from bruh");
        } else if (fromParts[0].trim().isEmpty()) {
            throw new DarkpoolException("task where???");
        }
    }

    /**
     * validateAndCreateEvent method is a method that validates the user input and creates an event task.
     * It checks if the user input is in the correct format and creates an event task.
     * @param desc the description of the event task
     * @param from the 'from' part of the user input
     * @param to the 'to' part of the user input
     * @return the event task created
     * @throws DarkpoolException if the user input is in the wrong format
     */
    public static Event validateAndCreateEvent(String desc, String from, String to) throws DarkpoolException {
        try {
            return new Event(desc, from.trim(), to.trim(), false);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException("bro you know what a date time format is?");
        }
    }
}
