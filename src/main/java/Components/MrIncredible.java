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
        return "Duke heard: " + input;
    }
    public static void main(String[] args) {
        ui.greet();

        while (true) {
            String input = scanner.nextLine().trim();
            Command command = parser.parseCommand(input);

            switch (command) {
                case BYE:
                    ui.sayBye();
                    return;
                case LIST:
                    listTasks();
                    break;
                case MARK:
                    markTask(input);
                    break;
                case TODO:
                    if (input.length() > 5) {
                        addToDoTask(input.substring(5).trim());
                    } else {
                        ui.handleError("The description of a todo cannot be empty.");
                    }
                    break;
                case DEADLINE:
                    if (input.length() > 9 && input.contains(" /by ")) {
                        addDeadlineTask(input.substring(9).trim());
                    } else {
                        ui.handleError("The deadline description or date is missing or improperly formatted. Use: deadline <description> /by <time>");
                    }
                    break;
                case EVENT:
                    if (input.length() > 6 && input.contains(" /from ") && input.contains(" /to ")) {
                        addEventTask(input.substring(6).trim());
                    } else {
                        ui.handleError("The event description, start, or end time is missing or improperly formatted. Use: event <description> /from <start> /to <end>");
                    }
                    break;
                case DELETE:
                    deleteTask(input);
                    break;
                case FIND:
                    findTask(input);
                    break;
                default:
                    ui.handleError("Sorry, I don't recognize that command. Please try again.");
                    break;
            }
        }
    }

    public static void deleteTask(String input) {
        try {
            int taskId = Integer.parseInt(input.substring(7).trim());
            Task removedTask = taskStorage.getTask(taskId);
            if (removedTask != null) {
                taskStorage.deleteTask(taskId);
                ui.showTaskRemoved(removedTask, taskStorage.getTaskCount());
            } else {
                ui.showInvalidTaskIdError();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTaskIdError();
        }
    }

    public static void addToDoTask(String description) {
        ToDo task = new ToDo(description);
        taskStorage.addTask(task);
        ui.showTaskAdded(task, taskStorage.getTaskCount());
    }

    public static void addDeadlineTask(String input) {
        try {
            String[] parts = input.split(" /by ");
            String description = parts[0];
            String by = parts[1];

            LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);

            Deadline task = new Deadline(description, byDateTime);
            taskStorage.addTask(task);
            ui.showTaskAdded(task, taskStorage.getTaskCount());
        } catch (DateTimeParseException e) {
            ui.showDateTimeParseError();
        }
    }

    public static void addEventTask(String input) {
        try {
            String[] parts = input.split(" /from | /to ");
            String description = parts[0];
            String from = parts[1];
            String to = parts[2];

            LocalDateTime fromDateTime = LocalDateTime.parse(from, formatter);
            LocalDateTime toDateTime = LocalDateTime.parse(to, formatter);

            Event task = new Event(description, fromDateTime, toDateTime);
            taskStorage.addTask(task);
            ui.showTaskAdded(task, taskStorage.getTaskCount());
        } catch (DateTimeParseException e) {
            ui.showDateTimeParseError();
        }
    }

    public static void markTask(String input) {
        int numberToUpdate = Integer.parseInt(input.substring(5));
        taskStorage.markTask(numberToUpdate);
        ui.showTaskMarked(taskStorage.getTask(numberToUpdate));
    }

    public static void listTasks() {
        ui.showTaskList(taskStorage);
    }

    public static void findTask(String input) {
        String[] parts = input.split(" ", 2);
        String keyword = parts[1];
        Map<Integer, Task> foundTasks = taskStorage.findTasks(keyword);
        ui.showFoundTasks(foundTasks);
    }
}