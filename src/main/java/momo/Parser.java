package momo;

import momo.MomoException;
import momo.command.CommandType;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class Parser {

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

        if (input.startsWith("mark"))  {
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

        throw new InvalidCommandException();
    }

    public static LocalDate parseDate(String input) throws DateTimeException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeException e) {
            System.out.println("Your storage file seems to be corrupted. Consider deleting it or I might delete your existence.");
            return null;
        }
    }
}


