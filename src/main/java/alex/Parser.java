package alex;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import alex.task.Deadline;
import alex.task.Event;
import alex.task.Task;
import alex.task.Todo;
import alex.command.Command;
import alex.command.AddCommand;
import alex.command.DeleteCommand;
import alex.command.ExitCommand;
import alex.command.ListCommand;
import alex.command.MarkCommand;

/**
 * Represents the class that helps to make sense of user inputs
 */
public class Parser {
    /**
     * Returns a Command class after interpreting user's input that matches user's command.
     * Throws an Exception if user did not input a valid command.
     *
     * @param fullCommand User input.
     * @return Command class.
     * @throws AlexException If invalid input is given by user.
     */
    public static Command parse(String fullCommand) throws AlexException {
        Scanner lineScanner = new Scanner(fullCommand);

        //Obtain the first word of user input
        String response = lineScanner.next();

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
        default:
            throw new AlexException("Sorry! Alex doesn't understand you. Please only start with 'todo', " +
                    "'deadline', 'event', 'mark', 'unmark', 'list' or 'bye'!");
        }
    }

    /**
     * Converts a date and time string into a LocalDateTime Object.
     *
     * @param deadline Date and time string of format "yyyy-MM-dd HHmm"
     * @return LocatDateTime object
     * @throws DateTimeParseException If date and time string not of "yyyy-MM-dd HHmm" format
     */
    public static LocalDateTime convertDateTime(String deadline) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Creates a Todo Task based on user description of the task.
     *
     * @param lineScanner Scanner object based on the line of text user entered.
     * @param arrOfStr ArrayList of Strings to store words entered by user.
     * @param isDone Boolean value representing whether the task is marked as done.
     * @return Todo Task object.
     * @throws AlexException If task description is empty.
     */
    public static Task makeTodoTask(Scanner lineScanner, ArrayList<String> arrOfStr, boolean isDone)
            throws AlexException {
        while (lineScanner.hasNext()) {
            arrOfStr.add(lineScanner.next());
        }
        if (arrOfStr.isEmpty()) {
            throw new AlexException("Oh no! Alex doesn't like that the todo task is blank :( " +
                    "You have to provide a task!");
        }
        return new Todo(String.join(" ", arrOfStr), isDone);
    }

    /**
     * Creates Deadline Task based on user description of the deadline and a provided deadline.
     *
     * @param lineScanner Scanner object based on the line entered by user.
     * @param arrOfStr ArrayList of Strings to store words entered by user.
     * @param isDone Boolean valule representing whether the task is marked as done.
     * @return Deadline Task
     * @throws AlexException If no description or no deadline is provided.
     */
    public static Task makeDeadlineTask(Scanner lineScanner, ArrayList<String> arrOfStr, boolean isDone)
            throws AlexException  {
        String description = "";
        String deadline = "";
        boolean hasProvidedDeadline = false;
        while (lineScanner.hasNext()) {
            String next = lineScanner.next();
            if (next.equals("/by")) {
                description = String.join(" ", arrOfStr);
                arrOfStr.clear();
                hasProvidedDeadline = true;
            } else {
                arrOfStr.add(next);
            }
        }

        deadline = String.join(" ", arrOfStr);

        if ((!hasProvidedDeadline && !deadline.isEmpty() && description.isEmpty())
                || (hasProvidedDeadline && !description.isEmpty() && deadline.isEmpty())) {
            throw new AlexException("Oh no! Alex doesn't like that no deadline date is provided :( Please provide a " +
                    "deadline date by writing '/by' followed by the deadline!");
        }
        if (description.isEmpty() ) {
            throw new AlexException("Oh no! Alex doesn't like that the deadline task is blank :( You have to provide " +
                    "a task!");
        }
        return new Deadline(description, isDone, convertDateTime(deadline));
    }

    /**
     * Creates an Event Task based on user description of the event as well as its start and end times.
     *
     * @param lineScanner Scanner object based on the line entered by user.
     * @param arrOfStr ArrayList of strings to store words entered by user.
     * @param isDone Boolean value representing whether the task is completed.
     * @return Event Task
     * @throws AlexException If no description, no start or no end time is provided or if end time is
     * provided before start time.
     */
    public static Task makeEventTask(Scanner lineScanner, ArrayList<String> arrOfStr, boolean isDone)
            throws AlexException  {
        String description = "";
        String start = "";
        boolean isStart = false;
        boolean isEnd = false;

        if (!lineScanner.hasNext()) {
            throw new AlexException("Oh no! Alex doesn't like that the event task is blank :( You have to provide " +
                    "a task!");
        }

        while (lineScanner.hasNext()) {
            String next = lineScanner.next();
            if (next.equals("/from")) {
                description = String.join(" ", arrOfStr);
                arrOfStr.clear();
                if (lineScanner.hasNext()) {
                    isStart = true;
                }
                if (isEnd) {
                    throw new AlexException("Oh no! Alex doesn't like that /to comes before /from :( You should " +
                            "write the start time first before the end time");
                }
            } else if (next.equals("/to")) {
                start = String.join(" ", arrOfStr);
                arrOfStr.clear();
                if (lineScanner.hasNext()) {
                    isEnd = true;
                }
            } else {
                arrOfStr.add(next);
            }
        }
        if (!isStart || start.isEmpty()) {
            throw new AlexException("Oh no! Alex doesn't like that no start time is provided :( You have to provide a " +
                    "start time with '/from' followed by the time!");
        }
        if (!isEnd) {
            throw new AlexException("Oh no! Alex doesn't like that no end time is provided :( You have to provide " +
                    "an end time with '/to' followed by the time!");
        }
        if (description.isEmpty()) {
            throw new AlexException("Oh no! Alex doesn't like that the event task is blank :( You have to provide " +
                    "a task!");
        }
        return new Event(description, isDone, convertDateTime(start),
                convertDateTime(String.join(" ", arrOfStr)));
    }
}
