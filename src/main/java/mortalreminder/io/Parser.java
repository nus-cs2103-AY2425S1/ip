package mortalreminder.io;

import java.util.Arrays;
import java.util.HashMap;

import mortalreminder.backend.CommandAlternativesStorage;
import mortalreminder.commands.Command;
import mortalreminder.commands.CommandAlternatives;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.Deadline;
import mortalreminder.tasks.Event;
import mortalreminder.tasks.Task;
import mortalreminder.tasks.ToDo;

// All javadocs in this class were autogenerated using ChatGPT

/**
 * Provides utility methods for parsing input strings into command and task objects.
 * <p>
 * The {@code Parser} class includes methods to interpret user input from the console
 * and parse data from files to create appropriate task objects.
 */
public class Parser {

    /**
     * Parses the user's input from the console and returns a corresponding {@code Command} object.
     * <p>
     * This method trims the input, splits it into parts, identifies the command word,
     * and determines the corresponding {@link CommandType}. If the command is not recognized,
     * it defaults to an unknown command type.
     *
     * @param input the user's input string.
     * @return a {@code Command} object corresponding to the parsed input.
     */
    public static Command parseInputFromUser(String input) throws MortalReminderException {
        // Trim and split the input into designated parts
        String[] splitInput = input.trim().replaceAll("\\s+", " ").split(" ");
        String commandWord = splitInput[0];
        int splitInputLength = splitInput.length;
        splitInput = Arrays.copyOfRange(splitInput, 1, splitInputLength);
        CommandType commandType = parseCommandWord(commandWord);
        return Command.initialise(commandType, splitInput);
    }

    /**
     * Parses a user input into a {@link CommandType}.
     */
    public static CommandType parseCommandWord(String commandWord) throws MortalReminderException {
        HashMap<String, CommandType> currentMappedCommands = CommandAlternativesStorage.loadCommandsFromFile();
        CommandAlternatives allMappedCommands = new CommandAlternatives(currentMappedCommands);
        if (allMappedCommands.getCommandType(commandWord) != null) {
            return allMappedCommands.getCommandType(commandWord);
        }
        return CommandType.UNKNOWN;
    }

    /**
     * Parses a string input from a file and returns a corresponding {@code Task} object.
     * <p>
     * This method splits the input string by the "|" delimiter, extracts the task type,
     * completion status, and description, and creates a specific {@code Task} object
     * (e.g., {@link ToDo}, {@link Deadline}, {@link Event}) based on the task type. This code
     * was generated using ChatGPT with some edits.
     *
     * @param input the input string read from a file.
     * @return the corresponding {@code Task} object, or {@code null} if the input is invalid.
     * @throws MortalReminderException if the file contains unrecognisable text.
     */
    public static Task parseInputFromFile(String input) throws MortalReminderException {

        String[] parts = input.split("\\|");
        String taskType = parts[0]; // The first letter indicating the task type
        boolean isDone = Boolean.parseBoolean(parts[1].trim());
        String description = parts[2].trim();

        // Create task object based on taskType
        switch (taskType) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            String deadline = parts[3].trim();
            return new Deadline(description, deadline, isDone);
        case "E":
            String fromTime = parts[3].trim();
            String toTime = parts[4].trim();
            return new Event(description, fromTime, toTime, isDone);
        default:
            throw new MortalReminderException("File might be corrupted! Please use clear_tasks to restart the file.");
        }
    }

}
