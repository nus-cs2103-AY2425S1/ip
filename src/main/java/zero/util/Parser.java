package zero.util;

import zero.task.Deadline;
import zero.task.Event;
import zero.task.Task;
import zero.task.Todo;
import zero.task.TaskList;

import zero.exception.ZeroException;

import zero.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static void parseCommand(String fullCommand, TaskList tasks, Ui ui) {
        try {
            if (fullCommand.equals("bye")) {
                ui.showGoodbye();
            } else if (fullCommand.equals("list")) {
                ui.listTasks(tasks);
            } else if (fullCommand.startsWith("delete")) {
                handleDelete(tasks, fullCommand, ui);
            } else if (fullCommand.startsWith("mark")) {
                handleMark(tasks, fullCommand, ui);
            } else if (fullCommand.startsWith("unmark")) {
                handleUnmark(tasks, fullCommand, ui);
            } else if (fullCommand.startsWith("todo")) {
                handleTodo(tasks, fullCommand, ui);
            } else if (fullCommand.startsWith("deadline")) {
                handleDeadline(tasks, fullCommand, ui);
            } else if (fullCommand.startsWith("event")) {
                handleEvent(tasks, fullCommand, ui);
            } else {
                throw new ZeroException("分かりません");
            }
        } catch (ZeroException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleDelete(TaskList tasks, String input, Ui ui) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int index = Integer.parseInt(strArr[1]) - 1;
            Task removedTask = tasks.deleteTask(index);
            ui.showTaskDeleted(removedTask, tasks.getSize());
        } catch (NumberFormatException e) {
            throw new ZeroException("Please enter a valid task number to delete.");
        }
    }

    //its getting abit too long so gonna shift it here
    private static void handleMark(TaskList tasks, String input, Ui ui) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int choice = Integer.parseInt(strArr[1]) - 1;  // convert to zero-based index
            tasks.getTask(choice).markAsDone();
            ui.showTaskMarked(tasks.getTask(choice));
        } catch (NumberFormatException e) {
            throw new ZeroException("PLease enter a valid task number to mark.");
        }
    }

    private static void handleUnmark(TaskList tasks, String input, Ui ui) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int choice = Integer.parseInt(strArr[1]) - 1;  // convert to zero-based index
            tasks.getTask(choice).markAsNotDone();
            ui.showTaskUnmarked(tasks.getTask(choice));
        } catch (NumberFormatException e) {
            throw new ZeroException("Please enter a valid task number to unmark.");
        }
    }

    private static void handleTodo(TaskList tasks, String input, Ui ui) throws ZeroException {
        if (input.trim().equals("todo")) {
            throw new ZeroException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();  // extract description
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        ui.showTaskAdded(newTodo, tasks.getSize());
    }

    private static void handleDeadline(TaskList tasks, String input, Ui ui) throws ZeroException {
        String[] parts = input.split("/by ", 2);
        if (parts.length < 2 || parts[0].trim().equals("deadline")) {
            throw new ZeroException("The description of a deadline or the date/time cannot be empty.");
        }
        String description = parts[0].substring(9).trim();  // extract description
        String byString = parts[1].trim();  // extract deadline
        LocalDateTime by = handleDate(byString);
        Deadline newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, tasks.getSize());
    }

    private static void handleEvent(TaskList tasks, String input, Ui ui) throws ZeroException {
        String[] parts = input.split("/from | /to ");
        if (parts.length < 3 || parts[0].trim().equals("event")) {
            throw new ZeroException("The description of an event or the date/time cannot be empty.");
        }
        String description = parts[0].substring(6).trim();  // extract description
        String fromString = parts[1].trim();  // extract start time
        String toString = parts[2].trim();  // extract end time
        LocalDateTime from = handleDate(fromString);
        LocalDateTime to = handleDate(toString);
        Event newEvent = new Event(description, from, to);
        tasks.addTask(newEvent);
        ui.showTaskAdded(newEvent, tasks.getSize());
    }

    public static LocalDateTime handleDate(String input) throws ZeroException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            if (input.length() <= 10) {
                // only date provided, default time to 00:00
                return LocalDateTime.parse(input + " 0000", dateTimeFormatter);
            } else {
                // date and time provided
                return LocalDateTime.parse(input, dateTimeFormatter);
            }
        } catch (DateTimeParseException e) {
            throw new ZeroException("Invalid date/time format. Please use 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
        }
    }
}