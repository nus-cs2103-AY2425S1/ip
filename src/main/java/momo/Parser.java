package momo;

import momo.command.CommandType;
import momo.exception.EmptyCommandException;
import momo.exception.InvalidCommandException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Handles parsing and interpreting user inputs, namely the command type and dates,
 * and determining the type of commands to be run or returning date object values
 */
public class Parser {

    /**
     * Parses user input string to determine the Command to be run
     *
     * @param input User's input string
     * @return The corresponing {@link CommandType} based on the user's input
     * @throws InvalidCommandException thrown if user's command does not match any existing command words
     * @throws EmptyCommandException   thrown if user input is empty
     */
    public static CommandType parseInput(String input) throws InvalidCommandException, EmptyCommandException {

        // Check if input is empty
        if (Objects.equals(input, "")) {
            throw new EmptyCommandException();
        }

        // Check if input is bye
        if (Objects.equals(input, "bye")) {
            return CommandType.BYE;
        }

        // Check if input is list
        if (Objects.equals(input, "list")) {
            return CommandType.LIST;
        }

        // Check if input is find
        if (input.startsWith("find")) {
            return CommandType.FIND;

        }

        // Check if input is delete
        if (input.startsWith("delete")) {
            return CommandType.DELETE;

        }

        if (input.startsWith("mark")) {
            return CommandType.MARK;
        }

        if (input.startsWith("unmark")) {
            return CommandType.UNMARK;
        }

        if (input.startsWith("todo")) {
            return CommandType.TODO;
        }

        if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        }

        if (input.startsWith("event")) {
            return CommandType.EVENT;
        }

        if (input.startsWith("archive")) {
            return CommandType.ARCHIVE;
        }

        throw new InvalidCommandException();
    }

    /**
     * Parses a date string into a LocalDate object
     *
     * @param input String input representing date
     * @return LocalDate object to represent date
     * @throws DateTimeException thrown if date input is not properly entered with a yyyy-mm-dd format
     */
    public static LocalDate parseDate(String input) throws DateTimeException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeException e) {
            System.out.println("Your storage file seems to be corrupted. Consider deleting it or I might delete your " +
                    "existence.");
            return null;
        }
    }
}


