package bopes;

import bopes.exception.BopesException;
import bopes.task.Deadline;
import bopes.task.Event;
import bopes.task.Task;
import bopes.task.TaskList;
import bopes.task.ToDo;

public class Parser {

    public static void parse(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        String[] commandWords = fullCommand.split(" ", 2);
        String commandType = commandWords[0];

        switch (commandType) {
            case "find":
                handleFindCommand(commandWords[1], tasks, ui);
                break;
            case "list":
                ui.showTasks(tasks);
                break;
            case "mark":
                handleMarkCommand(commandWords[1], tasks, ui, storage);
                break;
            case "unmark":
                handleUnmarkCommand(commandWords[1], tasks, ui, storage);
                break;
            case "delete":
                handleDeleteCommand(commandWords[1], tasks, ui, storage);
                break;
            default:
                handleAddTaskCommand(fullCommand, tasks, ui, storage);
                break;
        }
    }

    private static void handleMarkCommand(String input, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasks.markTask(index);
            ui.showMarkedTask(task);
            storage.saveTasks(tasks);
        } catch (NumberFormatException e) {
            throw BopesException.invalidNumberFormat();
        }
    }

    private static void handleUnmarkCommand(String input, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasks.unmarkTask(index);
            ui.showUnmarkedTask(task);
            storage.saveTasks(tasks);
        } catch (NumberFormatException e) {
            throw BopesException.invalidNumberFormat();
        }
    }

    private static void handleDeleteCommand(String input, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasks.getTasks().get(index);
            tasks.deleteTask(index);
            ui.showDeletedTask(task, tasks.getTasks().size());
            storage.saveTasks(tasks);
        } catch (NumberFormatException e) {
            throw BopesException.invalidNumberFormat();
        }
    }

    private static void handleAddTaskCommand(String input, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        Task newTask = null;
        try {
            if (input.startsWith("todo ")) {
                newTask = new ToDo(input.substring(5), false);
            } else if (input.startsWith("deadline ")) {
                String[] temp = input.substring(9).split(" /by ");
                if (temp.length == 2) {
                    newTask = new Deadline(temp[0], temp[1], false);
                } else {
                    throw BopesException.invalidDeadlineFormat();
                }
            } else if (input.startsWith("event ")) {
                String[] temp = input.substring(6).split(" /from | /to ");
                if (temp.length == 3) {
                    newTask = new Event(temp[0], temp[1], temp[2], false);
                } else {
                    throw BopesException.invalidEventFormat();
                }
            } else {
                throw BopesException.unknownCommand();
            }
            tasks.addTask(newTask);
            ui.showAddedTask(newTask, tasks.getTasks().size());
            storage.saveTasks(tasks);
        } catch (IllegalArgumentException e) {
            throw new BopesException(e.getMessage());
        }
    }

    public static Task parseTask(String taskData) throws BopesException {
        String[] data = taskData.split(" \\| ");
        if (data.length < 3) {
            throw new BopesException("Corrupted data: Insufficient task data in file.");
        }
        String taskType = data[0];
        boolean isDone = data[1].equals("1");  // Parse the done status
        String description = data[2];

        switch (taskType) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                return new Deadline(description, data[3], isDone);
            case "E":
                return new Event(description, data[3], data[4], isDone);
            default:
                throw new BopesException("Error: Unknown task type in file.");
        }
    }

    private static void handleFindCommand(String keyword, TaskList tasks, Ui ui) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(matchingTasks);
    }
}
