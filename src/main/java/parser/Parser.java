package parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import exceptions.EmptyTaskException;
import exceptions.InvalidInputException;
import exceptions.NoLastCommandToUndo;
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

        case "undo":
            return handleUndoCommand(slicedStrings, taskList, storage);

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

        int taskIndex;

        try {
            taskIndex = getTaskIndex(slicedStrings);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The task number should be a valid integer.");
        }

        if (isMarking) {
            taskList.markTask(taskIndex);
            storage.saveCommands("mark", taskList.getTask(taskIndex));
            saveTasksWithHandling(taskList, storage);
            return String.format("OK, I've marked this task as done:\n[%s][%s] %s",
                    taskList.getTask(taskIndex).getType(),
                    taskList.getTask(taskIndex).getStatusIcon(), taskList.getTask(taskIndex));
        } else {
            taskList.unmarkTask(taskIndex);
            storage.saveCommands("unmark", taskList.getTask(taskIndex));
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
        storage.saveCommands("todo", newTodo);
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
            storage.saveCommands("deadline", newDeadline);
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
            storage.saveCommands("event", newEvent);
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

        int taskIndex;

        try {
            taskIndex = getTaskIndex(slicedStrings);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The task number should be a valid integer.");
        }

        Task deletedTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        storage.saveCommands("delete", deletedTask);
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
     * Handles the "Undo" command to find tasks with the same keywords from the task list.
     * The user is supposed to enter how many times they want to undo their command (max 3)
     *
     * @param slicedStrings The user's input sliced Strings into an array.
     * @param taskList The task list containing all tasks.
     */
    private static String handleUndoCommand(
            String[] slicedStrings, TaskList taskList, Storage storage
    ) throws InvalidInputException {
        if (slicedStrings.length > 2) {
            throw new InvalidInputException("The format for undo command is undo or undo [number]!");
        }

        if (slicedStrings.length < 2) {
            // handles the default situation
            try {
                Map<String, Task> lastCommand = storage.lastCommand();
                handleSingleUndoCommand(taskList, storage, lastCommand);
                return "The previous command has been undone";
            } catch (NoLastCommandToUndo | TaskIndexOutOfBound e) {
                return e.getMessage();
            }
        }

        int numberOfTimesToUndo = getNumberOfTimesToUndo(slicedStrings, storage);
        for (int i = 1; i <= numberOfTimesToUndo; i++) {
            try {
                Map<String, Task> lastCommand = storage.lastCommand();
                handleSingleUndoCommand(taskList, storage, lastCommand);
            } catch (NoLastCommandToUndo | TaskIndexOutOfBound e) {
                return e.getMessage();
            }
        }
        return "The previous commands have been undone";
    }

    private static int getNumberOfTimesToUndo(String[] slicedStrings, Storage storage) throws InvalidInputException {
        int numberOfTimesToUndo;
        try {
            numberOfTimesToUndo = Integer.parseInt(slicedStrings[1]);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The task number should be a valid integer.");
        }
        if (numberOfTimesToUndo > storage.getCommandsSize()) {
            throw new InvalidInputException("The number of times "
                    + "you want to undo exceeds the total number of command entries!");
        }
        if (numberOfTimesToUndo <= 0) {
            throw new InvalidInputException("The number of times "
                    + "you want to undo is invalid!");
        }
        return numberOfTimesToUndo;
    }

    private static void handleSingleUndoCommand(
            TaskList taskList, Storage storage, Map<String, Task> lastCommand)
            throws TaskIndexOutOfBound {
        String keyword = lastCommand.keySet()
                .toArray(new String[0])[lastCommand.size() - 1].trim();
        Task task = lastCommand.get(lastCommand.keySet()
                .toArray(new String[0])[lastCommand.size() - 1]);
        int index = taskList.getTasks().indexOf(task);
        switch(keyword) {
        case("mark"):
            taskList.unmarkTask(index);
            saveTasksWithHandling(taskList, storage);
            break;
        case("unmark"):
            taskList.markTask(index);
            saveTasksWithHandling(taskList, storage);
            break;
        case("todo"):
        case("deadline"):
        case("event"):
            taskList.deleteTask(index);
            saveTasksWithHandling(taskList, storage);
            break;
        case("delete"):
            taskList.addTask(task);
            saveTasksWithHandling(taskList, storage);
            break;
        default:
        }
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
