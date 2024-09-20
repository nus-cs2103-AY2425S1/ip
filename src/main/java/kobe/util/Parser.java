package kobe.util;

import kobe.command.*;
import kobe.KobeException;

/**
 * Parses user input and converts it into a command to be executed by the Kobe chatbot application.
 * This class is responsible for interpreting the user's commands and converting them into the
 * appropriate {@link Command} objects.
 */
public class Parser {

    /**
     * Parses the user input into a specific {@link Command} object.
     * The method throws a {@link KobeException} if the input is invalid or the command is not recognized.
     *
     * @param fullCommand The full user input string to parse.
     * @return A {@link Command} object corresponding to the user's input.
     * @throws KobeException If the user input is invalid or cannot be parsed into a known command.
     */
    public static Command parse(String fullCommand) throws KobeException {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new KobeException("Command cannot be empty. Please enter a valid command.");
        }

        String[] words = fullCommand.trim().split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                return parseMarkCommand(words);

            case "unmark":
                return parseUnmarkCommand(words);

            case "delete":
                return parseDeleteCommand(words);

            case "todo":
            case "deadline":
            case "event":
                return parseAddCommand(fullCommand);

            case "find":
                return parseFindCommand(words);

            case "tag":
                return parseTagCommand(words);

            default:
                throw new KobeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the 'mark' command and ensures the task number is valid.
     *
     * @param words The array of command words.
     * @return The {@link MarkCommand} with the specified task number.
     * @throws KobeException If the task number is invalid or missing.
     */
    private static Command parseMarkCommand(String[] words) throws KobeException {
        if (words.length < 2) {
            throw new KobeException("The 'mark' command requires a task number. Usage: mark <task number>");
        }
        try {
            int taskNumber = Integer.parseInt(words[1]);
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new KobeException("Invalid task number for 'mark' command. Please enter a valid number.");
        }
    }

    /**
     * Parses the 'unmark' command and ensures the task number is valid.
     *
     * @param words The array of command words.
     * @return The {@link UnmarkCommand} with the specified task number.
     * @throws KobeException If the task number is invalid or missing.
     */
    private static Command parseUnmarkCommand(String[] words) throws KobeException {
        if (words.length < 2) {
            throw new KobeException("The 'unmark' command requires a task number. Usage: unmark <task number>");
        }
        try {
            int taskNumber = Integer.parseInt(words[1]);
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new KobeException("Invalid task number for 'unmark' command. Please enter a valid number.");
        }
    }

    /**
     * Parses the 'delete' command and ensures the task number is valid.
     *
     * @param words The array of command words.
     * @return The {@link DeleteCommand} with the specified task number.
     * @throws KobeException If the task number is invalid or missing.
     */
    private static Command parseDeleteCommand(String[] words) throws KobeException {
        if (words.length < 2) {
            throw new KobeException("The 'delete' command requires a task number. Usage: delete <task number>");
        }
        try {
            int taskNumber = Integer.parseInt(words[1]);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new KobeException("Invalid task number for 'delete' command. Please enter a valid number.");
        }
    }

    /**
     * Parses the 'todo', 'deadline', or 'event' commands and creates the appropriate AddCommand.
     *
     * @param fullCommand The full input string for adding tasks.
     * @return The {@link AddCommand} object corresponding to the task type.
     * @throws KobeException If the command is invalid.
     */
    private static Command parseAddCommand(String fullCommand) throws KobeException {
        return new AddCommand(fullCommand);
    }

    /**
     * Parses the 'find' command and ensures a keyword or tag is provided.
     *
     * @param words The array of command words.
     * @return The {@link FindCommand} with the specified keyword or tag.
     * @throws KobeException If the keyword is missing.
     */
    private static Command parseFindCommand(String[] words) throws KobeException {
        if (words.length < 2) {
            throw new KobeException("The 'find' command requires a keyword or tag. Usage: find <keyword or #tag>");
        }
        return new FindCommand(words[1]);
    }

    /**
     * Parses the 'tag' command and ensures a valid task number and tag are provided.
     *
     * @param words The array of command words.
     * @return The {@link TagCommand} with the specified task number and tag.
     * @throws KobeException If the task number or tag is missing or invalid.
     */
    private static Command parseTagCommand(String[] words) throws KobeException {
        if (words.length < 2) {
            throw new KobeException("The 'tag' command requires a task number and a tag. Usage: tag <task number> <#tag>");
        }
        String[] tagParts = words[1].trim().split(" ", 2);
        if (tagParts.length < 2) {
            throw new KobeException("Invalid 'tag' command format. Usage: tag <task number> <#tag>");
        }
        try {
            int taskNumber = Integer.parseInt(tagParts[0]);
            String tag = tagParts[1].trim().replace("#", "");
            if (tag.isEmpty()) {
                throw new KobeException("Tag cannot be empty. Please provide a valid tag.");
            }
            return new TagCommand(taskNumber, tag);
        } catch (NumberFormatException e) {
            throw new KobeException("Invalid task number for 'tag' command. Please enter a valid number.");
        }
    }
}
