package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.EmptyTaskException;
import exceptions.InvalidInputException;
import exceptions.TaskIndexOutOfBound;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * The Parser class is responsible for parsing and executing user commands.
 * It interprets the user's input and interacts with the TaskList, Ui, and Storage
 * classes to perform the appropriate actions.
 */
public class Parser {

    /**
     * Parses and executes the user's command.
     *
     * @param command   The command entered by the user (e.g. Todo XXX).
     * @param taskList  The TaskList object that holds all the tasks.
     * @param storage   The Storage object that handles saving and loading tasks from the file.
     * @return The result of the command as a string message.
     * @throws InvalidInputException if the user enters an unrecognized command or provides invalid input.
     * @throws EmptyTaskException    if the user attempts to add a task without providing a description.
     * @throws TaskIndexOutOfBound   if the user provides an index for a task that does not exist.
     */
    public static String parseUserCommand(
            String command, TaskList taskList, Storage storage
    ) throws InvalidInputException, EmptyTaskException, TaskIndexOutOfBound {
    assert command != null : "Command should not be empty";
    assert taskList != null : "TaskList should not be empty";
    assert storage != null : "Storage should not be empty";

    String[] slicedStrings = command.split(" ");
    String action = slicedStrings[0];

        switch (action) {
        case "list":
            return getTaskList(taskList.getTasks());

        case "mark":
            return handleMarkCommand(slicedStrings, taskList, storage, true);

        case "unmark":
            return handleMarkCommand(slicedStrings, taskList, storage, false);

        case "todo":
            return handleTodoCommand(slicedStrings, taskList, storage);

        case "deadline":
            return handleDeadlineCommand(slicedStrings, taskList, storage);

        case "event":
            return handleEventCommand(slicedStrings, taskList, storage);

        case "delete":
            return handleDeleteCommand(slicedStrings, taskList, storage);

        case "find":
            return handleFindCommand(slicedStrings, taskList);

        default:
            throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Handles the "mark" and "unmark" commands for marking or unmarking a task.
     *
     * @param slicedStrings  The user's input sliced Strings into an array.
     * @param taskList   The task list containing all tasks.
     * @param storage    The storage for saving tasks.
     * @param isMarking  A boolean that indicates whether to mark or unmark the task.
     * @return A string response indicating the result of marking or unmarking the task.
     * @throws TaskIndexOutOfBound if the task index provided is out of bounds.
     * @throws InvalidInputException if the task number is not provided.
     */
    private static String handleMarkCommand(
            String[] slicedStrings, TaskList taskList, Storage storage, boolean isMarking
    ) throws TaskIndexOutOfBound, InvalidInputException {
        if (slicedStrings.length < 2) {
            throw new InvalidInputException("Please provide a task number to mark or unmark.");
        }

        int taskIndex = getTaskIndex(slicedStrings);

        if (isMarking) {
            taskList.markTask(taskIndex);
            saveTasksWithHandling(taskList, storage);
            return String.format("OK, I've marked this task as done:\n[%s][%s] %s",
                    taskList.getTask(taskIndex).getType(),
                    taskList.getTask(taskIndex).getStatusIcon(), taskList.getTask(taskIndex));
        } else {
            taskList.unmarkTask(taskIndex);
            saveTasksWithHandling(taskList, storage);
            return String.format("OK, I've marked this task as not done yet:\n[%s][%s] %s",
                    taskList.getTask(taskIndex).getType(),
                    taskList.getTask(taskIndex).getStatusIcon(), taskList.getTask(taskIndex));
        }
    }

    private static int getTaskIndex(String[] slicedStrings) {
        return Integer.parseInt(slicedStrings[1]) - 1;
    }

    /**
     * Handles the "todo" command to add a new Todo task.
     *
     * @param slicedStrings  The user's input sliced Strings into an array.
     * @param taskList   The task list containing all tasks.
     * @param storage    The storage for saving tasks.
     * @return A string response confirming the addition of the task.
     * @throws EmptyTaskException if the task description is missing.
     */
    private static String handleTodoCommand(
            String[] slicedStrings, TaskList taskList, Storage storage
    ) throws EmptyTaskException {
        if (slicedStrings.length < 2) {
            throw new EmptyTaskException("todo");
        }
        Todo newTodo = new Todo();
        newTodo.convertStringToTask(slicedStrings);
        taskList.addTask(newTodo);
        saveTasksWithHandling(taskList, storage);
        return String.format("Got it. I've added this task:\n[%s][%s] %s\nNow you have %d tasks in the list",
                newTodo.getType(), newTodo.getStatusIcon(), newTodo, taskList.getTasks().size());
    }

    /**
     * Handles the "deadline" command to add a new Deadline task.
     *
     * @param slicedStrings  The user's input sliced Strings into an array.
     * @param taskList   The task list containing all tasks.
     * @param storage    The storage for saving tasks.
     * @return A string response confirming the addition of the task.
     * @throws EmptyTaskException if the task description is missing.
     */
    private static String handleDeadlineCommand(
            String[] slicedStrings, TaskList taskList, Storage storage
    ) throws EmptyTaskException {
        if (slicedStrings.length < 2) {
            throw new EmptyTaskException("deadline");
        }

        try {
            Deadline newDeadline = new Deadline();
            newDeadline.convertStringToTask(slicedStrings);
            taskList.addTask(newDeadline);
            saveTasksWithHandling(taskList, storage);
            return String.format("Got it. I've added this task:\n[%s][%s] %s\nNow you have %d tasks in the list",
                    newDeadline.getType(), newDeadline.getStatusIcon(), newDeadline, taskList.getTasks().size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Error saving task details: Invalid description or date.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "todo" command to add a new Event task.
     *
     * @param slicedStrings  The user's input sliced Strings into an array.
     * @param taskList   The task list containing all tasks.
     * @param storage    The storage for saving tasks.
     * @return A string response confirming the addition of the task.
     * @throws EmptyTaskException if the task description is missing.
     */
    private static String handleEventCommand(
            String[] slicedStrings, TaskList taskList, Storage storage
    ) throws EmptyTaskException {
        if (slicedStrings.length < 2) {
            throw new EmptyTaskException("event");
        }

        try {
            Event newEvent = new Event();
            newEvent.convertStringToTask(slicedStrings);
            taskList.addTask(newEvent);
            saveTasksWithHandling(taskList, storage);
            return String.format("Got it. I've added this task:\n[%s][%s] %s\nNow you have %d tasks in the list",
                    newEvent.getType(), newEvent.getStatusIcon(), newEvent, taskList.getTasks().size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Error saving task details. Please enter a valid description or date.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "delete" command to remove a task from the task list.
     *
     * @param slicedStrings  The user's input sliced Strings into an array.
     * @param taskList   The task list containing all tasks.
     * @param storage    The storage for saving tasks.
     * @return A string response confirming the deletion of the task.
     * @throws TaskIndexOutOfBound if the task index is out of bounds.
     * @throws InvalidInputException if the task number is not provided.
     */
    private static String handleDeleteCommand(
            String[] slicedStrings, TaskList taskList, Storage storage
    ) throws TaskIndexOutOfBound, InvalidInputException {
        if (slicedStrings.length < 2) {
            throw new InvalidInputException("Please provide a task number to delete.");
        }

        int taskIndex = getTaskIndex(slicedStrings);
        Task deletedTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        saveTasksWithHandling(taskList, storage);
        return String.format("Noted. I've removed this task:\n[%s][%s] %s\nNow you have %d tasks in the list",
                deletedTask.getType(), deletedTask.getStatusIcon(), deletedTask, taskList.getTasks().size());
    }

    /**
     * Handles the "find" command to find tasks with the same keywords from the task list.
     *
     * @param slicedStrings  The user's input sliced Strings into an array.
     * @param taskList   The task list containing all tasks.
     * @return A string response confirming the deletion of the task.
     * @throws InvalidInputException if the task number is not provided.
     */
    private static String handleFindCommand(
            String[] slicedStrings, TaskList taskList
    ) throws InvalidInputException {
        if (slicedStrings.length < 2) {
            throw new InvalidInputException("Please indicate what you want to find.");
        }

        String keyword = slicedStrings[1];
        List<Task> matchingTasks = taskList.getTasks().stream()
                .filter(task -> task.toString().contains(keyword))
                .collect(Collectors.toList());

        return getTaskList(matchingTasks);
    }

    /**
     * Saves the tasks entered by the user
     *
     * @param taskList The task list containing all tasks.
     * @param storage The storage containing all tasks.
     */
    private static void saveTasksWithHandling(TaskList taskList, Storage storage) {
        try {
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            // This error should now be handled by the caller if needed
            e.printStackTrace();
        }
    }

    /**
     * returns the tasks of the user
     *
     * @param taskList The task list containing all tasks.
     * @return return the task list of the user
     */
    private static String getTaskList(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return "Your task list is empty.";
        }
        return taskList.stream()
                .map(task -> String.format("[%s][%s] %s", task.getType(), task.getStatusIcon(), task))
                .collect(Collectors.joining("\n", "Here are the tasks in your list:\n", ""));
    }

    /**
     * Parses saved data from a file into a Task object.
     *
     * @param dataArr  The array of strings representing the saved data for a task.
     * @return The corresponding Task object, or null if the data is invalid.
     */
    public static Task parseSavedData(String[] dataArr) {
        Task task;
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
        default:
            task = null;
            break;
        }
        if (task != null) {
            task.convertSavedDataToTask(dataArr);
        }
        return task;
    }
}
