package rex.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import rex.command.Command;
import rex.exception.InvalidInputException;
import rex.task.TaskList;

/**
 * The {@code Parser} class provides utility methods to parse user input and save file content.
 * It includes methods to parse commands, tasks, and dates, as well as handle input validation.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");


    /**
     * Parses the user input into command and argument tokens.
     *
     * @param input The raw user input as a string.
     * @return A string array where the first element is the command and the second is the argument.
     * @throws InvalidInputException If the input is invalid based on the command.
     */
    public static String[] parseInput(String input) throws InvalidInputException {
        // Parse command and command argument
        String[] inputTokens = input.split(" ", 2);


        validateInput(inputTokens);

        // Return command if no error
        return inputTokens;
    }

    /**
     * Parses the contents of a file and loads tasks into the provided {@code TaskList}.
     *
     * @param list The {@code TaskList} instance where tasks will be loaded.
     * @param f The file to be parsed.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static void parseFile(TaskList list, File f) throws IOException {
        // Initialize reader to read from save file
        FileReader r = new FileReader(f);
        BufferedReader reader = new BufferedReader(r);
        String currentLine;

        // Iterate through each line in save file
        while ((currentLine = reader.readLine()) != null) {
            // Split line into tokens
            String[] taskTokens = currentLine.split(" \\| ");
            String taskType = taskTokens[0];
            // 1 if marked, 0 if unmarked
            int marked = Integer.parseInt(taskTokens[1]);
            boolean isMarked = marked == 1;
            String description = taskTokens[2];

            // Add to task list
            switch (taskType) {
            case "T":
                list.loadTask(description, isMarked);
                break;
            case "D":
                LocalDateTime by = parseDateTime(taskTokens[3]);
                list.loadTask(description, isMarked, by);
                break;
            case "E":
                LocalDateTime from = parseDateTime(taskTokens[3]);
                LocalDateTime to = parseDateTime(taskTokens[4]);
                list.loadTask(description, isMarked, from, to);
                break;
            default:
            }
        }

        reader.close();
        r.close();
    }

    /**
     * Parses the argument for a {@code Deadline} task to extract the description and deadline.
     *
     * @param argument The raw argument string containing the task description and deadline.
     * @return A string array where the first element is the description and the second is the deadline.
     * @throws InvalidInputException If the input is missing the "/by" keyword or other required elements.
     */
    public static String[] parseDeadline(String argument) throws InvalidInputException {
        String[] descriptionBy = argument.split("/by ", 2);
        if (descriptionBy.length < 2) {
            throw new InvalidInputException("/by not found in input!");
        }

        return descriptionBy;
    }

    /**
     * Parses the argument for an {@code Event} task to extract the description, start time, and end time.
     *
     * @param argument The raw argument string containing the task description, start time, and end time.
     * @return A string array where the first element is the description,
     *         the second is the start time, and the third is the end time.
     * @throws InvalidInputException If the input is missing the "/from" or "/to" keywords or other required elements.
     */
    public static String[] parseEvent(String argument) throws InvalidInputException {
        String[] tokens = argument.split("/from ", 2);
        if (tokens.length < 2) {
            throw new InvalidInputException("/from not found in input!");
        }

        String description = tokens[0];

        String[] fromTo = tokens[1].split(" /to ", 2);
        if (fromTo.length < 2) {
            throw new InvalidInputException("/to not found in input!");
        }

        return new String[] {description, fromTo[0], fromTo[1]};
    }

    /**
     * Parses a date and time string into a {@code LocalDateTime} object.
     *
     * @param dateTimeString The string containing the date and time in the format "dd-MM-yy HHmm".
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
    }

    /**
     * Validates the command input and its arguments, throwing an exception if invalid.
     *
     * @param inputTokens The array of command and argument tokens.
     * @throws InvalidInputException If the input is invalid for the given command.
     */

    private static void validateInput(String[] inputTokens) throws InvalidInputException {
        Command command = Command.inputToCommand(inputTokens[0]);

        switch (command) {
        case HELP:
        case BYE:
            if (inputTokens.length > 1) {
                String usageMessage = Command.usageMessage(command);
                throw new InvalidInputException("Too many arguments!\nUsage: " + usageMessage);
            }
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            if (inputTokens.length == 1) {
                String usageMessage = Command.usageMessage(command);
                throw new InvalidInputException("Task description cannot be empty!\nUsage: " + usageMessage);
            }
            break;
        case LIST:
            if (inputTokens.length > 1) {
                String usageMessage = Command.usageMessage(command);
                throw new InvalidInputException("Too MANY arguments!\nUsage: " + usageMessage);
            }
            break;
        case MARK:
        case UNMARK:
        case DELETE:
            if (inputTokens.length == 1) {
                String usageMessage = Command.usageMessage(command);
                throw new InvalidInputException("Too FEW arguments!\nUsage: " + usageMessage);
            }
            break;
        case RAWR:
            if (inputTokens.length > 1) {
                throw new InvalidInputException("");
            }
            break;
        default:
        }
    }
}
