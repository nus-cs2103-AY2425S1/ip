package cstwooneohthree.glados.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import cstwooneohthree.glados.enums.TaskType;
import cstwooneohthree.glados.exceptions.ArgumentsNotFoundException;
import cstwooneohthree.glados.exceptions.DateNotFoundException;
import cstwooneohthree.glados.exceptions.DateNotParsedException;
import cstwooneohthree.glados.exceptions.DateRangeNotFoundException;
import cstwooneohthree.glados.exceptions.DescriptionNotFoundException;
import cstwooneohthree.glados.exceptions.GladosException;
import cstwooneohthree.glados.exceptions.TaskNotFoundException;

/**
 * Parser class to parse names and data
 *
 * @author jayjay19630
 */
public class Parser {
    /**
     * Parses a task based on its taskType and command line arguments.
     *
     * @param taskType Type of task.
     * @param input Command line arguments passed by user.
     * @return ParsedInfo object with task description and corresponding dates.
     * @throws GladosException If arguments cannot be parsed correctly.
     */
    public static ParsedInfo parseTask(TaskType taskType, String input) throws GladosException {

        assert taskType == TaskType.TODO || taskType == TaskType.DEADLINE || taskType == TaskType.EVENT;

        switch (taskType) {
        case TODO:
            String todoDescription = input.trim();
            checkDescription(todoDescription);
            return new ParsedInfo(todoDescription, new LocalDate[0]);

        case DEADLINE:
            checkDescription(input.trim());
            String[] deadlineInputs = input.split("/by");
            String deadlineDescription = deadlineInputs[0].trim();
            checkDescription(deadlineDescription);

            if (deadlineInputs.length != 2 || deadlineInputs[1].trim().equals("")) {
                throw new DateNotFoundException();
            }

            return new ParsedInfo(
                    deadlineInputs[0].trim(),
                    new LocalDate[]{
                            parseDate(deadlineInputs[1].trim())});

        case EVENT:
            checkDescription(input.trim());
            String[] eventInputs = input.split("/from");
            String eventDescription = eventInputs[0].trim();
            checkDescription(eventDescription);

            if (eventInputs.length != 2) {
                throw new DateRangeNotFoundException();
            }

            String[] dateRange = eventInputs[1].split("/to");
            if (dateRange.length != 2 || dateRange[0].trim().equals("") || dateRange[1].trim().equals("")) {
                throw new DateRangeNotFoundException();
            }

            return new ParsedInfo(
                    eventInputs[0].trim(),
                    new LocalDate[]{
                            parseDate(dateRange[0].trim()),
                            parseDate(dateRange[1].trim())});
        default:
            throw new TaskNotFoundException();
        }
    }

    public static String[] parseUpdateArgs(String input) throws GladosException {
        String[] args = input.split(" ");
        try {
            Integer.valueOf(args[0]);
        } catch (NumberFormatException e) {
            throw new ArgumentsNotFoundException();
        }
        return args;
    }

    /**
     * Parses date.
     */
    public static LocalDate parseDate(String input) throws DateNotParsedException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new DateNotParsedException();
        }
    }

    /**
     * Parses command string.
     */
    public static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Parses command argument strings.
     * @param input Command arguments.
     * @return Parsed arguments.
     * @throws ArgumentsNotFoundException If arguments do not exist.
     */
    public static String parseArguments(String input) throws ArgumentsNotFoundException {
        String[] parsedArgs = input.split(" ", 2);
        if (parsedArgs.length <= 1 || parsedArgs[1].trim().equals("")) {
            throw new ArgumentsNotFoundException();
        }
        return parsedArgs[1];
    }

    private static void checkDescription(String description) throws DescriptionNotFoundException {
        if (description.equals("")) {
            throw new DescriptionNotFoundException();
        }
    }
}
