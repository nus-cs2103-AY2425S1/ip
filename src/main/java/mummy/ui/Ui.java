package mummy.ui;

import mummy.task.Deadline;
import mummy.task.Event;
import mummy.task.Task;
import mummy.task.ToDo;
import mummy.task.TaskList;
import mummy.task.TaskListException;
import mummy.utility.Echo;
import mummy.utility.Parser;
import mummy.utility.Storage;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class Ui {
    private final String logo;

    private final Storage storage;

    private final TaskList taskList;

    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE,
        EVENT, DELETE, UNKNOWN
    }

    public Ui(String logo, TaskList taskList, Storage storage) {
        this.logo = logo;
        this.taskList = taskList;
        this.storage = storage;
    }

    public void greet() {
        echo("Hello from\n"
                + logo + "\n"
                + "What can I do for you?\n");
    }

    public void listen(Scanner scanner) {
        while (scanner.hasNextLine()) {

            String input = scanner.nextLine();
            Command command;
            HashMap<String, String> arguments = Parser.parse(input);

            try {
                command = Command.valueOf(
                        arguments.getOrDefault("command", "")
                                .toUpperCase()
                );
            } catch (IllegalArgumentException exception) {
                command = Command.UNKNOWN;
            }

            try {
                switch (command) {
                case BYE:
                    onBye();
                    return;
                case LIST:
                    onList();
                    break;
                case MARK:
                    onMark(arguments);
                    break;
                case UNMARK:
                    onUnmark(arguments);
                    break;
                case TODO:
                    onToDo(arguments);
                    break;
                case DEADLINE:
                    onDeadline(arguments);
                    break;
                case EVENT:
                    onEvent(arguments);
                    break;
                case DELETE:
                    onDelete(arguments);
                    break;
                default:
                    throw new UiException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (UiException exception) {
                echo("OOPS!!!! " + exception.getMessage());
            }
        }
    }

    private void onBye() {
        echo("Bye. Hope to see you again soon!\n");
    }

    private void onList() {
        echo(taskList.toString());
    }

    private void onMark(HashMap<String, String> arguments) throws UiException {
        int taskIndex = parseIntOrDefault(
                arguments.getOrDefault("description", ""),
                -1);

        try {
            taskList.markTask(taskIndex);
            saveTaskListToStorage();
            echo("Nice! I've marked this mummy.task as done:\n\t" + taskList.get(taskIndex));
        } catch (TaskListException exception) {
            throw new UiException(exception.getMessage());
        }
    }

    private void onUnmark(HashMap<String, String> arguments) throws UiException {
        int taskIndex = parseIntOrDefault(
                arguments.getOrDefault("description", ""),
                -1);
        try {
            taskList.unmarkTask(taskIndex);
            saveTaskListToStorage();
            echo("OK, I've marked this mummy.task as not done yet:\n\t" + taskList.get(taskIndex));
        } catch (TaskListException exception) {
            throw new UiException(exception.getMessage());
        }
    }

    private void onToDo(HashMap<String, String> arguments) throws UiException {
        try {
            String description = arguments.getOrDefault("description", "");
            addTaskToStore(new ToDo(description));
        } catch (IndexOutOfBoundsException exception) {
            throw new UiException("description cannot be empty");
        }
    }

    private void onDeadline(HashMap<String, String> arguments) throws UiException {
        try {
            String description = arguments.getOrDefault("description", "");
            String dueBy = arguments.getOrDefault("by", "");

            addTaskToStore(new Deadline(description, dueBy));
        } catch (IndexOutOfBoundsException exception) {
            throw new UiException("Invalid argument: /by is required");
        } catch (DateTimeParseException exception) {
            throw new UiException("Invalid date format: " + exception.getMessage());
        }
    }

    private void onEvent(HashMap<String, String> arguments) throws UiException {
        try {
            String description = arguments.getOrDefault("description", "");
            String from = arguments.getOrDefault("from", "");
            String to = arguments.getOrDefault("to", "");

            addTaskToStore(new Event(description, from, to));
        } catch (IndexOutOfBoundsException exception) {
            throw new UiException("Invalid argument: /from and /to are required");
        }
    }

    private void onDelete(HashMap<String, String> arguments) throws UiException {
        int taskIndex = parseIntOrDefault(
                arguments.getOrDefault("description", ""),
                -1);

        try {
            Task task = taskList.remove(taskIndex);
            saveTaskListToStorage();
            echo(String.format(
                    "Noted. I've removed this mummy.task:\n\t%s\nNow you have %d tasks in the list.\n",
                    task, taskList.getCount()
            ));
        } catch (TaskListException exception) {
            throw new UiException(exception.getMessage());
        }
    }

    private void addTaskToStore(Task task) {
        taskList.add(task);
        saveTaskListToStorage();
        echo(String.format(
                "Got it. I've added this mummy.task:\n\t%s\nNow you have %d tasks in the list.\n",
                task, taskList.getCount()
        ));
    }

    private static void echo(String message) {
        new Echo(message).execute();
    }

    private static int parseIntOrDefault(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException exception) {
            return defaultValue;
        }
    }

    private void saveTaskListToStorage() {
        try {
            storage.save(taskList.toFileRecords());
        } catch (IOException e) {
            System.out.println("Something went wrong when saving to file: "
                    + e.getMessage());
        }
    }
}
