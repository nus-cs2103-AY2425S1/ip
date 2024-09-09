package Components;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import Chatbot.HarddiskStorage;
import Chatbot.TaskStorage;
import Chatbot.Parser;
import Chatbot.Command;
import Chatbot.Ui;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class MrIncredible {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HarddiskStorage harddiskStorage = new HarddiskStorage("./data/duke.txt");
    private static final TaskStorage taskStorage = new TaskStorage(harddiskStorage);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();

    public String getResponse(String input) {
        Command command = parser.parseCommand(input);
        return handleCommand(command, input);
    }

    private static String handleCommand(Command command, String input) {
        switch (command) {
            case BYE -> {
                return handleBye();
            }
            case LIST -> {
                return listTasks();
            }
            case MARK -> {
                return markTask(input);
            }
            case TODO -> {
                return handleToDoCommand(input);
            }
            case DEADLINE -> {
                return handleDeadlineCommand(input);
            }
            case EVENT -> {
                return handleEventCommand(input);
            }
            case DELETE -> {
                return deleteTask(input);
            }
            case FIND -> {
                return findTask(input);
            }
            default -> {
                return ui.handleError("Sorry, I don't recognize that command. Please try again.");
            }
        }
    }

    private static String handleBye() {
        return ui.sayBye();
    }

    private static String handleToDoCommand(String input) {
        String description = extractDescription(input, 5, "todo");
        if (description != null) {
            return addToDoTask(description);
        }
        return ui.handleError("The description of a todo cannot be empty.");
    }

    private static String handleDeadlineCommand(String input) {
        if (input.length() > 9 && input.contains(" /by ")) {
            return addDeadlineTask(input.substring(9).trim());
        } else {
            return ui.handleError("Invalid deadline command. Use: deadline <description> /by <time>");
        }
    }

    private static String handleEventCommand(String input) {
        if (input.length() > 6 && input.contains(" /from ") && input.contains(" /to ")) {
            return addEventTask(input.substring(6).trim());
        } else {
            return ui.handleError("Invalid event command. Use: event <description> /from <start> /to <end>");
        }
    }

    public static String deleteTask(String input) {
        try {
            int taskId = Integer.parseInt(input.substring(7).trim());
            Task removedTask = taskStorage.getTask(taskId);
            if (removedTask != null) {
                taskStorage.deleteTask(taskId);
                return ui.showTaskRemoved(removedTask, taskStorage.getTaskCount());
            } else {
                return ui.showInvalidTaskIdError();
            }
        } catch (NumberFormatException e) {
            return ui.showInvalidTaskIdError();
        }
    }

    public static String addToDoTask(String description) {
        ToDo task = new ToDo(description);
        taskStorage.addTask(task);
        return ui.showTaskAdded(task, taskStorage.getTaskCount());
    }

    public static String addDeadlineTask(String input) {
        try {
            String[] parts = input.split(" /by ");
            String description = parts[0];
            String by = parts[1];

            LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);

            Deadline task = new Deadline(description, byDateTime);
            taskStorage.addTask(task);
            return ui.showTaskAdded(task, taskStorage.getTaskCount());
        } catch (DateTimeParseException e) {
            return ui.showDateTimeParseError();
        }
    }

    public static String addEventTask(String input) {
        try {
            String[] parts = input.split(" /from | /to ");
            String description = parts[0];
            String from = parts[1];
            String to = parts[2];

            LocalDateTime fromDateTime = LocalDateTime.parse(from, formatter);
            LocalDateTime toDateTime = LocalDateTime.parse(to, formatter);

            Event task = new Event(description, fromDateTime, toDateTime);
            taskStorage.addTask(task);
            return ui.showTaskAdded(task, taskStorage.getTaskCount());
        } catch (DateTimeParseException e) {
            return ui.showDateTimeParseError();
        }
    }

    public static String markTask(String input) {
        try {
            int numberToUpdate = Integer.parseInt(input.substring(5).trim());
            taskStorage.markTask(numberToUpdate);
            return ui.showTaskMarked(taskStorage.getTask(numberToUpdate));
        } catch (NumberFormatException e) {
            return ui.showInvalidTaskIdError();
        }
    }

    public static String listTasks() {
        return ui.showTaskList(taskStorage);
    }

    public static String findTask(String input) {
        String keyword = extractKeyword(input);
        Map<Integer, Task> foundTasks = taskStorage.findTasks(keyword);
        return ui.showFoundTasks(foundTasks);
    }

    private static String extractDescription(String input, int commandLength, String commandName) {
        if (input.length() > commandLength) {
            return input.substring(commandLength).trim();
        } else {
            return ui.handleError("The description of a " + commandName + " cannot be empty.");
        }
    }

    private static String extractKeyword(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

}
