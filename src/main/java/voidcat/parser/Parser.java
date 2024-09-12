package voidcat.parser;

import voidcat.storage.Storage;
import voidcat.task.*;
import voidcat.exception.VoidCatException;
import voidcat.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

public class Parser {

    public String parseAndExecute(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws VoidCatException, IOException, SecurityException, IllegalArgumentException {
        String[] splitInput = fullCommand.split(" ", 2);
        String commandWord = splitInput[0];
        String arguments = splitInput.length > 1 ? splitInput[1] : "";
        String response = "";

        switch (commandWord.toLowerCase()) {
        case "bye":
            break;
        case "list":
            response = tasks.listTasks();
            break;
        case "delete":
            response = handleDeleteCommand(arguments, tasks, ui);
            break;
        case "mark":
            response = handleMarkCommand(arguments, tasks, ui);
            break;
        case "unmark":
            response = handleUnmarkCommand(arguments, tasks, ui);
            break;
        case "todo":
            response = handleAddToDoCommand(arguments, tasks, ui);
            break;
        case "deadline":
            response = handleAddDeadlineCommand(arguments, tasks, ui);
            break;
        case "event":
            response = handleAddEventCommand(arguments, tasks, ui);
            break;
        case "find":
            response = handleFindCommand(arguments, tasks);
            break;
        default:
            throw new VoidCatException("AH!! My apologies, I don't know what that means =T^T=");
        }
        
        storage.save(tasks);  // Save tasks to file after every command that modifies the list
        return response;
    }

    private String handleDeleteCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Hm.. I dont know which to delete! Give me the task number please.");
        } else {
            int taskIndex = parseTaskIndex(arguments, tasks);
            Task removedTask = tasks.deleteTask(taskIndex);
            return Ui.showDeleteTaskMessage(removedTask, tasks.size());
        }
    }

    private String handleMarkCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Hm.. I dont know which to mark! Give me the task number please.");
        } else {
            int taskIndex = parseTaskIndex(arguments, tasks);
            Task markedTask = tasks.markTaskAsDone(taskIndex);
            return Ui.showMarkTaskMessage(markedTask);
        }
    }

    private String handleUnmarkCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Hm.. I dont know which to unmark! Give me the task number please.");
        } else {
            int taskIndex = parseTaskIndex(arguments, tasks);
            Task unmarkedTask = tasks.unmarkTaskAsDone(taskIndex);
            return Ui.showUnmarkTaskMessage(unmarkedTask);
        }
    }


    private String handleAddToDoCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Meow! The description of a todo can't be empty!");
        } else {
            ToDo newTask = new ToDo(arguments);
            tasks.addTask(newTask);
            return Ui.showAddTaskMessage(newTask, tasks.size());
        }
    }

    private String handleAddDeadlineCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        String[] details = arguments.split(" /by ");
        if (details.length != 2) {
            throw new VoidCatException("AH! The description of a deadline and the deadline can't be empty!\nRemember to put a /by after the description!");
        } else {
            try {
                Deadline newTask = new Deadline(details[0], details[1]);
                tasks.addTask(newTask);
                return Ui.showAddTaskMessage(newTask, tasks.size());
            } catch (DateTimeParseException d) {
                throw new VoidCatException("AH! Check if:\n\t1. Input time is valid\n\t2. Format of the 24h date time is in yyyy-mm-dd hhmm");
            }
        }
    }

    private String handleAddEventCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException, IllegalArgumentException {
        String[] details = arguments.split(" /from | /to ");
        if (details.length != 3) {
            throw new VoidCatException("AH! The description of an event, and the start and end time can't be empty!\nRemember to put a /from and /to after the description!");
        } else {
            try {
                LocalDateTime dfrom = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"));
                LocalDateTime dto = LocalDateTime.parse(details[2], DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"));
                if (dfrom.isAfter(dto)) {
                    throw new IllegalArgumentException("AH! The /from date time must be before the /after");
                }
                Event newTask = new Event(details[0], details[1], details[2]);
                tasks.addTask(newTask);
                return Ui.showAddTaskMessage(newTask, tasks.size());
            } catch (DateTimeParseException d) {
                throw new VoidCatException("AH! Check if:\n\t1. Input time is valid\n\t2. Format of the 24h date time is in yyyy-mm-dd hhmm");
            }
        }
    }

    private String handleFindCommand(String arguments, TaskList tasks) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Please provide a keyword to find.");
        } else {
            return tasks.findTasks(arguments);
        }
    }

    private int parseTaskIndex(String arguments, TaskList tasks) throws VoidCatException {
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new VoidCatException("OOPS!!! The task number provided is invalid.");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new VoidCatException("OOPS!!! The task number provided is invalid.");
        }
    }
}
