package parser;

import exceptions.EmptyTaskException;
import exceptions.InvalidInputException;
import exceptions.TaskIndexOutOfBound;
import storage.Storage;
import task.*;
import ui.Ui;

import java.io.IOException;
import java.util.Arrays;

public class Parser {
    public static void parseCommand(String command, TaskList taskList, Ui ui, Storage storage) throws InvalidInputException, EmptyTaskException, TaskIndexOutOfBound {
        String[] slicedStr = command.split(" ");
        String action = slicedStr[0];

        switch (action) {
        case "list":
            ui.showTaskList(taskList.getTasks());
            break;

        case "mark":
            handleMarkCommand(slicedStr, taskList, ui, storage, true);
            break;

        case "unmark":
            handleMarkCommand(slicedStr, taskList, ui, storage, false);
            break;

        case "todo":
            handleTodoCommand(slicedStr, taskList, ui, storage);
            break;

        case "deadline":
            handleDeadlineCommand(slicedStr, taskList, ui, storage);
            break;

        case "event":
            handleEventCommand(slicedStr, taskList, ui, storage);
            break;

        case "delete":
            handleDeleteCommand(slicedStr, taskList, ui, storage);
            break;

        default:
            throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void handleMarkCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage, boolean isMarking) throws TaskIndexOutOfBound, InvalidInputException {
        if (slicedStr.length < 2) {
            throw new InvalidInputException("Please provide a task number to mark or unmark.");
        }

        int taskIndex = Integer.parseInt(slicedStr[1]) - 1;

        if (isMarking) {
            taskList.markTask(taskIndex);
            ui.showMarkTask(taskList.getTask(taskIndex));
        } else {
            taskList.unmarkTask(taskIndex);
            ui.showUnmarkTask(taskList.getTask(taskIndex));
        }

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }

    private static void handleTodoCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage) throws EmptyTaskException {
        if (slicedStr.length < 2) {
            throw new EmptyTaskException("todo");
        }
        Todo newTodo = new Todo();
        newTodo.convertStringToTask(slicedStr);
        taskList.addTask(newTodo);
        ui.showAddTask(newTodo, taskList.getTasks().size());

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }

    private static void handleDeadlineCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage) throws EmptyTaskException {
        if (slicedStr.length < 2) {
            throw new EmptyTaskException("deadline");
        }

        try {
            Deadline newDeadline = new Deadline();
            newDeadline.convertStringToTask(slicedStr);
            taskList.addTask(newDeadline);
            ui.showAddTask(newDeadline, taskList.getTasks().size());
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showErrorMessage("Error saving tasks details: " + e.getMessage());
        }
    }

    private static void handleEventCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage) throws EmptyTaskException {
        if (slicedStr.length < 2) {
            throw new EmptyTaskException("event");
        }

        try {
            Event newEvent = new Event();
            newEvent.convertStringToTask(slicedStr);
            taskList.addTask(newEvent);
            ui.showAddTask(newEvent, taskList.getTasks().size());
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showErrorMessage("Error saving tasks details. Please enter a valid description or date");
        }
    }

    private static void handleDeleteCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage) throws TaskIndexOutOfBound, InvalidInputException {
        if (slicedStr.length < 2) {
                throw new InvalidInputException("Please provide a task number to delete.");
        }

        int taskIndex = Integer.parseInt(slicedStr[1]) - 1;
        Task deletedTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        ui.showDeleteTask(deletedTask, taskList.getTasks().size());

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }


    public static Task parseSavedData(String[] dataArr) {
        Task task = null;
        switch (dataArr[0]) {
        case "T":
            task = new Todo();
            break;
        case "D":
            task = new Deadline();
            break;
        case "E":
            task = new Event();
            break;
        }
        if (task != null) {
            task.convertSavedDataToTask(dataArr);
        }
        return task;
    }
}
