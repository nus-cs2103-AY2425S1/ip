package voidcat.parser;

import voidcat.storage.Storage;
import voidcat.task.*;
import voidcat.exception.VoidException;
import voidcat.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

public class Parser {

    public void parseAndExecute(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws VoidException, IOException, SecurityException, IllegalArgumentException {
        String[] splitInput = fullCommand.split(" ", 2);
        String commandWord = splitInput[0];
        String arguments = splitInput.length > 1 ? splitInput[1] : "";

        switch (commandWord.toLowerCase()) {
        case "bye":
            break;
        case "list":
            tasks.listTasks();
            break;
        case "delete":
            handleDeleteCommand(arguments, tasks, ui);
            break;
        case "mark":
            handleMarkCommand(arguments, tasks, ui);
            break;
        case "unmark":
            handleUnmarkCommand(arguments, tasks, ui);
            break;
        case "todo":
            handleAddToDoCommand(arguments, tasks, ui);
            break;
        case "deadline":
            handleAddDeadlineCommand(arguments, tasks, ui);
            break;
        case "event":
            handleAddEventCommand(arguments, tasks, ui);
            break;
        default:
            throw new VoidException("AH!! My apologies, I don't know what that means =T^T=");
        }

        storage.save(tasks);  // Save tasks to file after every command that modifies the list
    }

    private void handleDeleteCommand(String arguments, TaskList tasks, Ui ui) throws VoidException {
        if (arguments.isBlank()) {
            throw new VoidException("Hm.. I dont know which to delete! Give me the task number please.");
        } else {
            int taskIndex = parseTaskIndex(arguments, tasks);
            Task removedTask = tasks.deleteTask(taskIndex);
            ui.showDeleteTaskMessage(removedTask, tasks.size());
        }
    }

    private void handleMarkCommand(String arguments, TaskList tasks, Ui ui) throws VoidException {
        if (arguments.isBlank()) {
            throw new VoidException("Hm.. I dont know which to mark! Give me the task number please.");
        } else {
            int taskIndex = parseTaskIndex(arguments, tasks);
            Task markedTask = tasks.markTaskAsDone(taskIndex);
            ui.showMarkTaskMessage(markedTask);
        }
    }

    private void handleUnmarkCommand(String arguments, TaskList tasks, Ui ui) throws VoidException {
        if (arguments.isBlank()) {
            throw new VoidException("Hm.. I dont know which to unmark! Give me the task number please.");
        } else {
            int taskIndex = parseTaskIndex(arguments, tasks);
            Task unmarkedTask = tasks.unmarkTaskAsDone(taskIndex);
            ui.showUnmarkTaskMessage(unmarkedTask);
        }
    }


    private void handleAddToDoCommand(String arguments, TaskList tasks, Ui ui) throws VoidException {
        if (arguments.isBlank()) {
            throw new VoidException("Meow! The description of a todo can't be empty!");
        } else {
            ToDo newTask = new ToDo(arguments);
            tasks.addTask(newTask);
            ui.showAddTaskMessage(newTask, tasks.size());
        }
    }

    private void handleAddDeadlineCommand(String arguments, TaskList tasks, Ui ui) throws VoidException {
        if (arguments.isBlank()) {
            throw new VoidException("Meow! The description of a deadline can't be empty!");
        } else {
            String[] details = arguments.split(" /by ");
            if (details.length != 2) {
                throw new VoidException("AH! The description of a deadline and the deadline can't be empty!\n\tRemember to put a /by after the description!");
            } else {
                try {
                    Deadline newTask = new Deadline(details[0], details[1]);
                    tasks.addTask(newTask);
                    ui.showAddTaskMessage(newTask, tasks.size());
                } catch (DateTimeParseException d) {
                    Ui.showMessageAndLines("AH! Check if:\n\t1. Input time is valid\n\t2. Format of the 24h date time is in yyyy-mm-dd hhmm");
                }
            }
        }
    }

    private void handleAddEventCommand(String arguments, TaskList tasks, Ui ui) throws VoidException, IllegalArgumentException {
        if (arguments.isBlank()) {
            throw new VoidException("Meow! The description of an event can't be empty!");
        } else {
            String[] details = arguments.split(" /from | /to ");
            if (details.length != 3) {
                throw new VoidException("AH! The description of an event, and the start and end time can't be empty!\n\tRemember to put a /from and /to after the description!");
            } else {
                try {
                    LocalDateTime dfrom = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"));
                    LocalDateTime dto = LocalDateTime.parse(details[2], DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"));
                    if (dfrom.isAfter(dto)) {
                        throw new IllegalArgumentException("AH! The /from date time must be before the /after");
                    }
                    Event newTask = new Event(details[0], details[1], details[2]);
                    tasks.addTask(newTask);
                    ui.showAddTaskMessage(newTask, tasks.size());
                } catch (DateTimeParseException d) {
                    Ui.showMessageAndLines("AH! Check if:\n\t1. Input time is valid\n\t2. Format of the 24h date time is in yyyy-mm-dd hhmm");
                }
            }
        }
    }

    private int parseTaskIndex(String arguments, TaskList tasks) throws VoidException {
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new VoidException("OOPS!!! The task number provided is invalid.");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new VoidException("OOPS!!! The task number provided is invalid.");
        }
    }
}
