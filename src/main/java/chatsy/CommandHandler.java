package chatsy;

import chatsy.exceptions.EmptyDescriptionException;
import chatsy.exceptions.InvalidCommandException;
import chatsy.exceptions.InvalidTaskIndexException;
import chatsy.tasks.DeadlineTask;
import chatsy.tasks.EventTask;
import chatsy.tasks.TodoTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandHandler {
    private final TaskManager taskManager;
    private final UI ui;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public CommandHandler(TaskManager taskManager, UI ui) {
        this.taskManager = taskManager;
        this.ui = ui;
    }

    public void handle(String command) throws InvalidCommandException, EmptyDescriptionException, InvalidTaskIndexException {
        String[] parts = command.split(" ", 2);
        String action = parts[0];

        switch (action) {
            case "bye":
                Chatsy.isRunning = false;
                break;
            case "list":
                ui.output(taskManager.listTasks());
                break;
            case "mark":
                handleMarkUnmark(parts, true);
                break;
            case "unmark":
                handleMarkUnmark(parts, false);
                break;
            case "delete":
                handleDelete(parts);
                break;
            case "todo":
                handleTodoTask(parts);
                break;
            case "deadline":
                handleDeadlineTask(parts);
                break;
            case "event":
                handleEventTask(parts);
                break;
            case "find":
                handleFindTask(parts);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    private void handleMarkUnmark(String[] parts, boolean isMark) throws InvalidTaskIndexException {
        if (parts.length > 1) {
            int taskNumber = Integer.parseInt(parts[1]);
            if (isMark) {
                taskManager.markTask(taskNumber);
                ui.output("Nice! I've marked this task as done:\n  " + taskManager.getTasks().get(taskNumber - 1));
            } else {
                taskManager.unmarkTask(taskNumber);
                ui.output("OK, I've marked this task as not done yet:\n  " + taskManager.getTasks().get(taskNumber - 1));
            }
        } else {
            ui.output("Please specify the task number to " + (isMark ? "mark." : "unmark."));
        }
    }

    private void handleDelete(String[] parts) throws InvalidTaskIndexException {
        if (parts.length > 1) {
            int taskNumber = Integer.parseInt(parts[1]);
            taskManager.deleteTask(taskNumber);
            ui.output("Noted. I've removed this task.\nNow you have " + taskManager.getTasks().size() + " tasks in the list.");
        } else {
            ui.output("Please specify the task number to delete.");
        }
    }

    private void handleTodoTask(String[] parts) throws EmptyDescriptionException {
        if (parts.length > 1) {
            taskManager.addTask(new TodoTask(parts[1]));
            ui.output("Got it. I've added this task.\nNow you have " + taskManager.getTasks().size() + " tasks in the list.");
        } else {
            throw new EmptyDescriptionException();
        }
    }

    private void handleDeadlineTask(String[] parts) throws EmptyDescriptionException {
        if (parts.length > 1) {
            String[] deadlineParts = parts[1].split(" /by ", 2);
            if (deadlineParts.length > 1) {
                try {
                    LocalDate by = LocalDate.parse(deadlineParts[1], DATE_FORMATTER);
                    taskManager.addTask(new DeadlineTask(deadlineParts[0], by));
                    ui.output("Got it. I've added this task.\nNow you have " + taskManager.getTasks().size() + " tasks in the list.");
                } catch (DateTimeParseException e) {
                    ui.output("Please enter the deadline in the correct format (yyyy-MM-dd).");
                }
            } else {
                ui.output("Please specify the deadline in the format: description /by yyyy-MM-dd");
            }
        } else {
            throw new EmptyDescriptionException();
        }
    }

    private void handleEventTask(String[] parts) throws EmptyDescriptionException {
        if (parts.length > 1) {
            String[] eventParts = parts[1].split(" /from | /to ", 3);
            if (eventParts.length == 3) {
                try {
                    LocalDateTime from = LocalDateTime.parse(eventParts[1], DATE_TIME_FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(eventParts[2], DATE_TIME_FORMATTER);
                    taskManager.addTask(new EventTask(eventParts[0], from, to));
                    ui.output("Got it. I've added this task.\nNow you have " + taskManager.getTasks().size() + " tasks in the list.");
                } catch (DateTimeParseException e) {
                    ui.output("Please enter the event times in the correct format (yyyy-MM-dd HH:mm).");
                }
            } else {
                ui.output("Please specify the event in the format: description /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm");
            }
        } else {
            throw new EmptyDescriptionException();
        }
    }

    private void handleFindTask(String[] parts) throws EmptyDescriptionException {
        if (parts.length > 1) {
            String keyword = parts[1];
            String result = taskManager.findTasks(keyword);
            if (result.isEmpty()) {
                ui.output("No matching tasks found.");
            } else {
                ui.output("Here are the matching tasks in your list:\n" + result);
            }
        } else {
            throw new EmptyDescriptionException();
        }
    }
}

