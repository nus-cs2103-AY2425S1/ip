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

public class Parser {
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
    public static LocalDateTime convertDateTime(String deadline) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(deadline, formatter);
    }

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
