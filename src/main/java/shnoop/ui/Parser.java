package shnoop.ui;

import shnoop.command.AddCommand;
import shnoop.command.Command;
import shnoop.command.DeleteCommand;
import shnoop.command.ExitCommand;
import shnoop.command.FindCommand;
import shnoop.command.ListCommand;
import shnoop.command.MarkCommand;
import shnoop.exceptions.ShnoopException;

/**
 * Reads and understands user input in the UI, and processes the relevant commands to be executed by Shnoop.
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
        FIND,
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
    public Commands getTaskType(String str) {
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
     * Checks if a command input fulfils specific criteria.
     *
     * @param idx Index from which to check if String exists.
     * @param str String to be checked.
     * @return True if String is not empty from specified idx, otherwise false
     */
    private static boolean isEmptyFromIdx(int idx, String str) {
        if (idx < 0 || str == null) {
            return false;
        }

        return str.substring(idx, str.length()).trim().length() == 0;
    }

    /**
     * Checks if command fulfils certain specified criteria.
     *
     * @param length Length of command.
     * @param target Valid minimal length of command.
     * @param idx Index to check command from.
     * @param input Input String command.
     * @param commandParts Array-form of the commands split by whitespaces.
     * @param b True to perform Integer parsing checks on command input.
     * @return True if command fails any specific criterion, otherwise false.
     */
    private static boolean isInvalidCommand(int length, int target,
                                            int idx, String input, String[] commandParts, boolean ... b) {
        if (length < target) {
            return true;
        }

        if (isEmptyFromIdx(idx, input)) {
            return true;
        }

        if (commandParts.length < 2) {
            return true;
        }

        if (commandParts[1].isEmpty()) {
            return true;
        }

        if (b.length != 0 && b[0] == true) {
            if (!canBeInteger(input.substring(idx, length))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines the relevant Command to be executed based on the String input.
     *
     * @param input String representation of a command to be run based on user input.
     * @return Command that should be run based on the user input.
     * @throws ShnoopException If the input is not a valid command.
     */
    public static Command parse(String input) throws ShnoopException {
        String improperInput =
                "You could travel the world, but nothing comes close to choosing a task type.\n\n"
                        + "I have a few California Gorls for you to try out. \n\n "
                        + "Try typing 'todo', 'event' or 'deadline' followed by stating the task description. \n"
                        + "Other commands you could try out too are list, mark, unmark, find, delete \n"
                        + "And don't tell anyone else I told you this but you can also type 'list 1' to sort tasks!\n"
                        + "But perhaps the most imporant of all, you MUST say 'bye' to me before leaving!"
                        + " Or else Imma forget all about your sorry donkey!";
        String[] commandParts = input.split(" ");
        int length = input.length();


        if (!contains((commandParts[0].toUpperCase()))) {
            throw new ShnoopException(improperInput);
        }
        Commands command = Commands.valueOf(commandParts[0].toUpperCase());

        switch (command) {
        case LIST:
            if (commandParts.length >= 2) {
                try {
                    return new ListCommand(Integer.parseInt(commandParts[1]));
                } catch (NumberFormatException e) {
                    throw new ShnoopException(improperInput);
                }
            }
            if (commandParts[0].toLowerCase().equals("list")
                    || commandParts[0].toLowerCase().equals("list ")
                    || (length >= 5 && isEmptyFromIdx(5, input))
                    || (commandParts.length >= 2 && commandParts[1].isEmpty())) {
                return new ListCommand();
            }
        case TODO:
            try {
                if (!startsWithTodo(input, length) || isEmptyFromIdx(5, input)
                        || commandParts[1].isEmpty()) {
                    throw new ShnoopException(improperInput);
                }
                return new AddCommand(input, Commands.TODO);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ShnoopException(improperInput);
            }

        case DEADLINE:
            try {
                if (!startsWithDeadline(input, length) || isEmptyFromIdx(9, input)
                        || commandParts[1].isEmpty()) {
                    throw new ShnoopException(improperInput);
                }
                return new AddCommand(input, Commands.DEADLINE);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ShnoopException(improperInput);
            }
        case EVENT:
            try {
                if (!startsWithEvent(input, length) || isEmptyFromIdx(6, input)
                        || commandParts[1].isEmpty()) {
                    throw new ShnoopException(improperInput);
                }
                return new AddCommand(input, Commands.EVENT);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ShnoopException(improperInput);
            }
        case FIND:
            if (isInvalidCommand(length, 6, 5, input, commandParts)) {
                throw new ShnoopException(improperInput);
            }
            return new FindCommand(input);
        case DELETE:
            if (isInvalidCommand(length,8, 7, input, commandParts, true)) {
                throw new ShnoopException(improperInput);
            }
            return new DeleteCommand(input);
        case MARK:
            if (isInvalidCommand(length, 6, 5, input, commandParts, true)) {
                throw new ShnoopException(improperInput);
            }
            return new MarkCommand(input, true);
        case UNMARK:
            if (isInvalidCommand(length, 8, 7, input, commandParts, true)) {
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
