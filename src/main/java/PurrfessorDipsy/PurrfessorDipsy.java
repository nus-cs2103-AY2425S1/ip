import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.ArrayList;

import Storage.Storage;
import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;
import Exception.InvalidCommandException;
import Exception.UnknownCommandException;
import Utilities.DateTimeParser;

public class PurrfessorDipsy {
    private static Ui ui;
    private static boolean isRunning = true; // to allow for a more graceful exit


    public static void main(String[] args) {
        ui = new Ui();
        Scanner inputScanner = new Scanner(System.in);
        taskTable = Storage.loadTasksFromFile();
        ui.printWelcomeMessage();
        while (isRunning) {
            try {
                String userInput = inputScanner.nextLine().trim();
                processCommand(userInput);
            } catch (InvalidCommandException | UnknownCommandException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
        inputScanner.close();
    }



    // HANDLE COMMANDS

    private static void handleTaskCreation(String userInput, Command command) throws InvalidCommandException {
        switch (command) {
            case TODO -> createTodoTask(userInput);
            case DEADLINE -> createDeadlineTask(userInput);
            case EVENT -> createEventTask(userInput);
        }
    }

    private static void createTodoTask(String userInput) throws InvalidCommandException {
        Matcher matcher = TODO_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            String description = matcher.group(1);
            saveToMemory(new ToDo(description));
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_TODO);
        }
    }

    private static void createDeadlineTask(String userInput) throws InvalidCommandException {
        Matcher matcher = DEADLINE_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String by = matcher.group(2);
            try {
                LocalDate parsedBy = DateTimeParser.parseDate(by);
                saveToMemory(new Deadline(description, parsedBy));
            } catch (DateTimeParseException e) {
                printDateParseErrorMessage(by);
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DEADLINE);
        }
    }

    private static void createEventTask(String userInput) throws InvalidCommandException {
        Matcher matcher = EVENT_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String start = matcher.group(2);
            String end = matcher.group(3);
            try {
                LocalDate parsedStart = DateTimeParser.parseDate(start);
                LocalDate parsedEnd = DateTimeParser.parseDate(end);
                saveToMemory(new Event(description, parsedStart, parsedEnd));
            } catch (DateTimeParseException e) {
                printDateParseErrorMessage(e.getParsedString());
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_EVENT);
        }
    }

    private static void handleListCommand(String userInput) {
        String[] parts = userInput.trim().split("\\s+");
        if (parts.length == 1) {
            // Case where input is 'list'
            ui.printListOfTasks(taskTable);
        } else if (parts.length == 2) {
            // Case where input is 'list <date>'
            try {
                LocalDate date = LocalDate.parse(parts[1]);
                ui.printListOfTasks(filterTasksByDate(date));
            } catch (DateTimeParseException e) {
                printDateParseErrorMessage(parts[1]);
            }
        }
    }

    private static void handleDeleteCommand(String userInput) throws InvalidCommandException {
        Matcher matcher = DELETE_PATTERN.matcher(userInput);

        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            deleteTaskAtIndex(index);
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DELETE_COMMAND);
        }
    }

    // Other util methods used for performing commands.
    private static void exitProgram() {
        isRunning = false; // Set the loop control flag to false to exit the loop gracefully.
    }

    private static void printDateParseErrorMessage(String date) {
        System.out.println("Invalid date format: " + date +
                           " Please enter the date in the format yyyy-MM-dd (e.g., 2024-08-25).");
    }

    public static ArrayList<Task> filterTasksByDate(LocalDate date) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : taskTable) {
            if (date.equals(t.getRelevantDate())) {
                res.add(t);
            }
        }
        return res;
    }
}
