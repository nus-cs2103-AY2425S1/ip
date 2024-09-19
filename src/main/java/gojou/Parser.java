package gojou;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import gojou.command.AddCommand;
import gojou.command.Command;
import gojou.command.DeleteCommand;
import gojou.command.ExitCommand;
import gojou.command.FindCommand;
import gojou.command.ListCommand;
import gojou.command.MarkCommand;
import gojou.task.Deadline;
import gojou.task.Event;
import gojou.task.Priority;
import gojou.task.Task;
import gojou.task.Todo;

/**
 * Helps to parse user inputs and convert them into appropriate commands and tasks.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     * Throws an exception if the input is invalid.
     *
     * @param fullCommand User input command string.
     * @return Command object corresponding to the user input.
     * @throws GojouException If the input does not match any valid command.
     */
    public static Command parse(String fullCommand) throws GojouException {
        assert !fullCommand.isEmpty() : "Empty command";
        Scanner lineScanner = new Scanner(fullCommand);

        // Obtain the first word of user input
        String response = lineScanner.next();

        // Creates respective command classes to handle different functions based on user input
        switch (response) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark", "unmark":
            return new MarkCommand(lineScanner, response);
        case "delete":
            return new DeleteCommand(lineScanner);
        case "todo", "deadline", "event":
            return new AddCommand(lineScanner, response);
        case "find":
            return new FindCommand(lineScanner);
        default:
            throw new GojouException("Huh, that went right over my head. Even the "
                    + "strongest needs a little clarification sometimes! Please only start with 'todo', "
                    + "'deadline', 'event', 'mark', 'unmark', 'list', 'delete', 'find' or 'bye'!");
        }
    }

    /**
     * Converts a date and time string into a LocalDateTime object.
     *
     * @param deadline Date and time string in the format "yyyy-MM-dd HHmm".
     * @return LocalDateTime object corresponding to the provided date and time string.
     * @throws DateTimeParseException If the date and time string is not in the correct format.
     */
    public static LocalDateTime convertDateTime(String deadline) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Reads the priority level from the user input and converts it into a Priority enum value.
     * The input should be in the format "//priority", where "priority" can be "high", "medium", or "low".
     *
     * @param word The string representing the user input for task priority.
     * @return A Priority enum value corresponding to the provided input ("high", "medium", "low").
     * @throws GojouException If the input does not match a valid priority level.
     */
    public static Priority readTaskPriority(String word) throws GojouException {
        String priorityInput = word.substring(2);

        switch (priorityInput) {
        case "high":
            return Priority.HIGH;
        case "medium":
            return Priority.MEDIUM;
        case "low":
            return Priority.LOW;
        default:
            throw new GojouException("Hey! Please provide a valid priority! Either '//high', "
                    + "'//medium' or '//low'");
        }
    }

    /**
     * Creates a Todo task based on user description.
     *
     * @param lineScanner Scanner object based on the line of text entered by the user.
     * @param arrOfStr ArrayList of strings to store words entered by the user.
     * @param isDone Boolean value indicating whether the task is marked as done.
     * @return A Todo Task object.
     * @throws GojouException If the task description is empty.
     */
    public static Task makeTodoTask(Scanner lineScanner, ArrayList<String> arrOfStr, boolean isDone)
            throws GojouException {
        Priority priority = Priority.NONE;
        while (lineScanner.hasNext()) {
            priority = getPriority(lineScanner, arrOfStr, priority);
        }

        if (arrOfStr.isEmpty()) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part of "
                    + "getting stronger. Let's try that again, shall we? Please provide a todo task");
        }

        return new Todo(String.join(" ", arrOfStr), isDone, priority);
    }

    private static Priority getPriority(Scanner lineScanner, ArrayList<String> arrOfStr, Priority priority) throws GojouException {
        String word = lineScanner.next();
        if (word.startsWith("//")) {
            priority = readTaskPriority(word);
        } else {
            arrOfStr.add(word);
        }
        return priority;
    }

    /**
     * Creates a Deadline task based on user description and provided deadline.
     *
     * @param lineScanner Scanner object based on the line entered by the user.
     * @param arrOfStr ArrayList of strings to store words entered by the user.
     * @param isDone Boolean value indicating whether the task is marked as done.
     * @return A Deadline Task object.
     * @throws GojouException If no description or deadline is provided, or if provided information is incorrect.
     */
    public static Task makeDeadlineTask(Scanner lineScanner, ArrayList<String> arrOfStr, boolean isDone)
            throws GojouException {
        String description = "";
        boolean hasProvidedDeadline = false;
        Priority priority = Priority.NONE;

        while (lineScanner.hasNext()) {
            String word = lineScanner.next();
            if (word.startsWith("//")) {
                priority = readTaskPriority(word);
            } else if (word.equals("/by")) {
                description = String.join(" ", arrOfStr);
                arrOfStr.clear();
                hasProvidedDeadline = true;
            } else {
                arrOfStr.add(word);
            }
        }

        String deadline = String.join(" ", arrOfStr);
        checkErroneousInput(hasProvidedDeadline, deadline, description);

        return new Deadline(description, isDone, priority, convertDateTime(deadline));
    }

    private static void checkErroneousInput(boolean hasProvidedDeadline, String deadline, String description) throws GojouException {
        boolean hasDescriptionButNoSlashBy = !hasProvidedDeadline
                && !deadline.isEmpty() && description.isEmpty();
        boolean hasDescriptionAndSlashByButNoDeadline = hasProvidedDeadline
                && !description.isEmpty() && deadline.isEmpty();

        if ((hasDescriptionButNoSlashBy
                || hasDescriptionAndSlashByButNoDeadline)) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part of"
                    + " getting stronger. Let's try that again, shall we? Please provide a "
                    + "deadline date by writing '/by' followed by the deadline");
        }
        if (description.isEmpty()) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part of "
                    + "getting stronger. Let's try that again, shall we? Please provide a deadline task");
        }
    }

    /**
     * Creates an Event task based on user description, start time, and end time.
     *
     * @param lineScanner Scanner object based on the line entered by the user.
     * @param arrOfStr ArrayList of strings to store words entered by the user.
     * @param isDone Boolean value indicating whether the task is completed.
     * @return An Event Task object.
     * @throws GojouException If no description, start time, or end time is provided, or end time is before start time.
     */
    public static Task makeEventTask(Scanner lineScanner, ArrayList<String> arrOfStr, boolean isDone)
            throws GojouException {
        checkIfTaskIsProvided(lineScanner);

        return getEvent(lineScanner, arrOfStr, isDone);
    }

    private static Event getEvent(Scanner lineScanner, ArrayList<String> arrOfStr, boolean isDone) throws GojouException {
        String description = "";
        String start = "";
        boolean isStart = false;
        boolean isEnd = false;
        Priority priority = Priority.NONE;

        //creates description, start and end strings based on user input to create event task
        while (lineScanner.hasNext()) {
            String word = lineScanner.next();
            if (word.startsWith("//")) {
                priority = readTaskPriority(word);
            } else if (word.equals("/from")) {
                description = String.join(" ", arrOfStr);
                arrOfStr.clear();
                isStart = checkIfStartDateIsProvided(lineScanner, isStart);
                checkIfEndDateProvidedBeforeStartDate(isEnd);
            } else if (word.equals("/to")) {
                start = String.join(" ", arrOfStr);
                arrOfStr.clear();
                isEnd = checkIfEndDateIsProvided(lineScanner, isEnd);
            } else {
                arrOfStr.add(word);
            }
        }
        checkErroneousInput(isStart, start, isEnd, description);

        return new Event(description, isDone, priority, convertDateTime(start),
                convertDateTime(String.join(" ", arrOfStr)));
    }

    private static boolean checkIfEndDateIsProvided(Scanner lineScanner, boolean isEnd) {
        if (lineScanner.hasNext()) {
            isEnd = true;
        }
        return isEnd;
    }

    private static void checkIfEndDateProvidedBeforeStartDate(boolean isEnd) throws GojouException {
        if (isEnd) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just"
                    + " part of getting stronger. Let's try that again, shall we? /to should not"
                    + "come before /from. Write the start time first before the end time");
        }
    }

    private static boolean checkIfStartDateIsProvided(Scanner lineScanner, boolean isStart) {
        if (lineScanner.hasNext()) {
            isStart = true;
        }
        return isStart;
    }

    private static void checkIfTaskIsProvided(Scanner lineScanner) throws GojouException {
        if (!lineScanner.hasNext()) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part of "
                    + "getting stronger. Let's try that again, shall we? Please provide an event task");
        }
    }

    private static void checkErroneousInput(boolean isStart, String start, boolean isEnd, String description) throws GojouException {
        if (!isStart || start.isEmpty()) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part of "
                    + "getting stronger. Let's try that again, shall we? Please provide a start time with '/from' "
                    + "followed by the time");
        }
        if (!isEnd) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part of "
                    + "getting stronger. Let's try that again, shall we? Please provide an end time with '/to' "
                    + "followed by the time!");
        }
        if (description.isEmpty()) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part of "
                    + "getting stronger. Let's try that again, shall we? Please provide an event task!");
        }
    }
}

