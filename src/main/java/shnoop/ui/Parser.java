package shnoop.ui;

import shnoop.command.*;
import shnoop.exceptions.*;

import java.io.IOException;

/**
 * Reads and understands user input in the UI, and processes the relevant commands to be executed.
 */
public class Parser {

    /**
     * Enums representing all the possible command keywords as well as available Task types.
     */
    public enum Commands {
        DELETE,
        MARK,
        UNMARK,
        BYE,
        LIST,
        TODO,
        EVENT,
        DEADLINE,
        UNDEFINED
    }

    /**
     * Determines if String indicates a Todo task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Todo ".
     */
    public static boolean startsWithTodo(String str, int length) {
        return (length >= 6 && str.toLowerCase().startsWith("todo "));
    }

    /**
     * Determines if String indicates an Event task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Event ".
     */
    public static boolean startsWithEvent(String str, int length) {
        return (length >= 7 && str.toLowerCase().startsWith("event "));
    }

    /**
     * Determines if String indicates a Deadline task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Deadline ".
     */
    public static boolean startsWithDeadline(String str, int length) {
        return (length >= 10 && str.toLowerCase().startsWith("deadline "));
    }


    /**
     * Determines the type of Task based on the command / user input.
     *
     * @param str User input to be read.
     * @return Enum representing the Task type.
     */
    public Commands getTaskType(String str){
        int length = str.length();
        if (startsWithTodo(str, length)) {
            return Commands.TODO;
        } else if (startsWithEvent(str, length)) {
            return Commands.EVENT;
        } else if (startsWithDeadline(str, length)) {
            return Commands.DEADLINE;
        } else {
            return Commands.UNDEFINED;
        }
    }

    /**
     * Determines if a part of a String can be interpreted as an Integer.
     *
     * @param str String to be analysed.
     * @return True if String can be expressed as an Integer.
     */
    public static boolean canBeInteger(String str) {
        if (str == null || str == "") {
            return false;
        }

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // @@author Richard H
    // From https://stackoverflow.com/questions/4936819/java-check-if-enum-contains-a-given-string
    // Reused with minor modifications

    /**
     * Determines if the specified String is an element within the Enum Commands.
     *
     * @param test String to be checked and compared with.
     * @return True if the String is an element in the Enum Commands.
     */
    public static boolean contains(String test) {
        for (Commands c : Commands.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
    // @@author Richard H

    /**
     * Determines the relevant Command to be executed based on the String input.
     *
     * @param input String representation of a command to be run based on user input.
     * @return Command that should be run based on the user input.
     * @throws ShnoopException If the input is not a valid command.
     */
    public static Command parse(String input) throws ShnoopException {
        String improperInput =
                "✿ Shnoop ✿: You could travel the world, but nothing comes close to choosing a task type.\n" +
                "✿ Shnoop ✿: Try typing 'todo', 'event' or 'deadline' followed by stating the task description.";
        String[] commandParts = input.split(" ");
        int length = input.length();


        if (!contains((commandParts[0].toUpperCase()))) {
            throw new ShnoopException(improperInput);
        }
        Commands command = Commands.valueOf(commandParts[0].toUpperCase());

        switch (command) {
        case LIST:
            return new ListCommand();
        case TODO:
            try {
                if (!startsWithTodo(input,length) || commandParts[1].isEmpty()) {
                    throw new ShnoopException(improperInput);
                }
                return new AddCommand(input, Commands.TODO);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ShnoopException(improperInput);
            }

        case DEADLINE:
            try {
                if (!startsWithDeadline(input, length) || commandParts[1].isEmpty()) {
                    throw new ShnoopException(improperInput);
                }
                return new AddCommand(input, Commands.DEADLINE);
            } catch (ArrayIndexOutOfBoundsException e) {
            throw new ShnoopException(improperInput);
        }
        case EVENT:
            try {
                if (!startsWithEvent(input, length) || commandParts[1].isEmpty()) {
                    throw new ShnoopException(improperInput);
                }
                return new AddCommand(input, Commands.EVENT);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ShnoopException(improperInput);
            }
        case DELETE:
            if (length < 8 || commandParts[1].isEmpty() || !canBeInteger(input.substring(7, length))) {
                throw new ShnoopException(improperInput);
            }
            return new DeleteCommand(input);
        case MARK:
            if (length < 6 || commandParts[1].isEmpty() || !canBeInteger(input.substring(5, length))) {
                throw new ShnoopException(improperInput);
            }
            return new MarkCommand(input, true);
        case UNMARK:
            if (length < 8 ||  commandParts[1].isEmpty() || !canBeInteger(input.substring(7, length))) {
                throw new ShnoopException(improperInput);
            }
            return new MarkCommand(input, false);
        case BYE:
            return new ExitCommand();
        default:
            throw new ShnoopException(improperInput);
        }
    }
}
