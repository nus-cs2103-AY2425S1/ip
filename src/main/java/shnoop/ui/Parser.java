package shnoop.ui;

import shnoop.command.*;
import shnoop.exceptions.*;

import java.io.IOException;

public class Parser {

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
     * @return True if String starts with "Todo "
     */
    public static boolean startsWithTodo(String str, int length) {
        return (length >= 6 && str.toLowerCase().startsWith("todo "));
    }

    /**
     * Determines if String indicates a Event task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Event "
     */
    public static boolean startsWithEvent(String str, int length) {
        return (length >= 7 && str.toLowerCase().startsWith("event "));
    }

    /**
     * Determines if String indicates a Deadline task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Deadline "
     */
    public static boolean startsWithDeadline(String str, int length) {
        return (length >= 10 && str.toLowerCase().startsWith("deadline "));
    }


    public Commands getTaskType (String str){
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
     * Returns the relevant string excluding Todo, Event, or Deadline tag.
     *
     * @param str User input to sieve through.
     * @return String excluding task type.
     */
    public String getTaskDetails (String str){
        Parser.Commands taskType = getTaskType(str);
        int length = str.length();

        switch (taskType) {
        case TODO:
            return str.substring(5, length);
        case EVENT:
            return str.substring(6, length);
        case DEADLINE:
            return str.substring(9, length);
        default:
            return str;
        }
    }

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
    public static boolean contains(String test) {
        for (Commands c : Commands.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
    // @@author Richard H

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
