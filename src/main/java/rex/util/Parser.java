package rex.util;

import rex.command.Command;
import rex.exception.InvalidInputException;
import rex.task.TaskList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");


    public static String[] parseInput(String input) throws InvalidInputException {
        // Parse rex.command and rex.command argument
        String[] inputTokens = input.split(" ", 2);

        // Error check input argument based on rex.command
        validateInput(inputTokens);

        // Return rex.command if no error
        return inputTokens;
    }

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

            // Add to rex.task list
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
            }
        }

        reader.close();
        r.close();
    }

    public static String[] parseDeadline(String argument) throws InvalidInputException {
        String[] descriptionBy = argument.split("/by ", 2);
        if (descriptionBy.length < 2) {
            throw new InvalidInputException("/by not found in input!");
        }

        return descriptionBy;
    }

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

    public static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
    }

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
        }
    }
}
