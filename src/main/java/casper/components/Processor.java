package casper.components;

import static casper.utils.DateTimeUtils.convertStringToDateTime;

import java.util.Arrays;
import java.util.List;

import casper.Casper;
import casper.exceptions.CustomIOException;
import casper.exceptions.IndexOutOfRangeException;
import casper.exceptions.InvalidArgumentException;
import casper.exceptions.InvalidCommandException;
import casper.exceptions.InvalidDateTimeException;
import casper.exceptions.InvalidIndexException;
import casper.exceptions.MissingArgumentException;
import casper.exceptions.NoArgumentException;
import casper.tasks.Deadline;
import casper.tasks.Event;
import casper.tasks.ToDo;
import casper.tasks.comparators.AlphabeticalComparator;
import casper.tasks.comparators.StatusComparator;
import casper.tasks.comparators.TimeComparator;
import casper.tasks.comparators.TypeComparator;

//CHECKSTYLE.OFF: Regexp
/**
 * Processes user input commands and manages tasks in the task list.
 */
public class Processor {

    private static final List<String> VALID_METHODS = Arrays.asList("alphabetical", "type", "time");

    /**
     * Processes the user input command and updates the task list accordingly.
     *
     * @param input The user input command to process.
     * @param taskList The task list to which the command will be applied.
     * @return A string message indicating the result of processing the command.
     * @throws CustomIOException If an I/O error occurs while interacting with the storage.
     * @throws InvalidIndexException If the task index provided in the command is invalid.
     * @throws NoArgumentException If a required argument is missing for a command.
     * @throws IndexOutOfRangeException If the task index is out of range of the current task list.
     * @throws InvalidCommandException If the command is invalid or not recognized.
     * @throws MissingArgumentException If required arguments are missing for task creation commands.
     * @throws InvalidDateTimeException If there is an issue with the date and time format provided.
     */
    public static String processInput(String input, TaskList taskList) throws CustomIOException,
            InvalidIndexException, NoArgumentException, IndexOutOfRangeException, InvalidCommandException,
            MissingArgumentException, InvalidDateTimeException, InvalidArgumentException {

        String[] inputParts = Parser.parseInputToCommandAndArgument(input);
        String command = inputParts[0];
        String argument = inputParts.length > 1 ? inputParts[1] : null;

        return switch (command) {
        case "bye" -> handleBye();
        case "list" -> handleList(taskList);
        case "sort" -> handleSort(argument, taskList);
        case "mark", "unmark", "delete" -> handleTaskModification(command, argument, taskList);
        case "find" -> handleFind(argument, taskList);
        case "todo", "deadline", "event" -> handleTaskCreation(command, argument, taskList);
        default -> throw new InvalidCommandException(command);
        };
    }

    /**
     * Handles the "bye" command, returning a farewell message.
     *
     * @return A farewell message string.
     */
    private static String handleBye() {
        return "Bye! You can close this window now or continue chatting anytime you want.";
    }

    /**
     * Handles the "list" command, returning the string representation of the task list.
     *
     * @param taskList The task list to retrieve the string representation from.
     * @return The string representation of the task list.
     */
    private static String handleList(TaskList taskList) {
        return taskList.toString();
    }

    private static String handleSort(String argument, TaskList taskList) throws NoArgumentException,
            MissingArgumentException, InvalidArgumentException, CustomIOException {
        Parser.argumentCheck(argument, "sort");

        String[] splitArgumentBy = Parser.parseSortArgument(argument);

        if (!splitArgumentBy[0].equals("/by") || splitArgumentBy[1].equals("")) {
            throw new MissingArgumentException("sort", "/by",
                    "sort /by [method], where [method] can be `alphabetical`, `type`, `time`, `status`");
        }

        switch (splitArgumentBy[1]) {
        case "alphabetical" -> {
            taskList.sortTasks(new AlphabeticalComparator());
        }
        case "type" -> {
            taskList.sortTasks(new TypeComparator());
        }
        case "time" -> {
            taskList.sortTasks(new TimeComparator());
        }
        case "status" -> {
            taskList.sortTasks(new StatusComparator());
        }
        default -> throw new InvalidArgumentException("sort", "/by", splitArgumentBy[1],
                "`alphabetical`, `type`, `time`,`status`");
        }

        Storage.replaceAllSavedTasks(Casper.FILE_PATH, taskList.toSaveString());
        return "I've sorted the tasks as requested!\n" + taskList;

    }

    /**
     * Handles task modification commands ("mark", "unmark", "delete").
     *
     * @param command The command to process ("mark", "unmark", or "delete").
     * @param argument The argument for the command (task index).
     * @param taskList The task list to modify.
     * @return A string message indicating the result of the operation.
     * @throws CustomIOException If an I/O error occurs while interacting with the storage.
     * @throws NoArgumentException If the provided argument is missing or invalid.
     * @throws InvalidIndexException If the specified task index is invalid.
     * @throws IndexOutOfRangeException If the specified task index is out of the allowable range.
     * @throws InvalidCommandException If the command is invalid or not recognized.
     */
    private static String handleTaskModification(String command, String argument, TaskList taskList) throws
            CustomIOException, NoArgumentException, InvalidIndexException, IndexOutOfRangeException,
            InvalidCommandException {
        Parser.argumentCheck(argument, command);
        taskList.checkIndexValidity(argument, command);

        int taskIndex = Integer.parseInt(argument) - 1;

        switch (command) {
        case "mark" -> {
            taskList.markTask(taskIndex);
            Storage.editTaskInSavedTasks(Casper.FILE_PATH,
                    taskList.getTaskSaveString(taskIndex),
                    taskIndex + 1);
            return "Nice! I've marked this task as done:\n" + taskList.getTaskString(taskIndex);
        }
        case "unmark" -> {
            taskList.unmarkTask(taskIndex);
            Storage.editTaskInSavedTasks(Casper.FILE_PATH,
                    taskList.getTaskSaveString(taskIndex),
                    taskIndex + 1);
            return "I have unmarked this task:\n" + taskList.getTaskString(taskIndex);
        }
        case "delete" -> {
            String toBeDeletedTaskString = taskList.getTaskString(taskIndex);
            taskList.removeTask(taskIndex);
            Storage.removeTaskFromSavedTasks(Casper.FILE_PATH, taskIndex + 1);
            return "I have deleted the task:\n" + toBeDeletedTaskString;
        }
        default -> throw new InvalidCommandException(command);
        }
    }

    /**
     * Handles the "find" command to search for tasks containing the specified keyword.
     *
     * @param argument The keyword to search for.
     * @param taskList The task list to search in.
     * @return A string message with the search results.
     * @throws NoArgumentException If the keyword argument is missing or invalid.
     */
    private static String handleFind(String argument, TaskList taskList) throws NoArgumentException {
        Parser.argumentCheck(argument, "find");
        return Stringifier.getFoundTasks(taskList.find(argument), argument);
    }

    /**
     * Handles task creation commands ("todo", "deadline", "event").
     *
     * @param command The command to process ("todo", "deadline", or "event").
     * @param argument The argument for the command (task description and additional details).
     * @param taskList The task list to add the task to.
     * @return A string message indicating the result of the task addition.
     * @throws CustomIOException If an I/O error occurs while interacting with the storage.
     * @throws MissingArgumentException If required arguments are missing for task creation.
     * @throws InvalidDateTimeException If the provided date/time format is invalid.
     * @throws NoArgumentException If the argument for the command is missing or invalid.
     * @throws InvalidCommandException If the command is invalid or not recognized.
     */
    private static String handleTaskCreation(String command, String argument, TaskList taskList) throws
            CustomIOException, MissingArgumentException, InvalidDateTimeException, NoArgumentException,
            InvalidCommandException {
        Parser.argumentCheck(argument, command);

        switch (command) {
        case "todo" -> {
            taskList.addTask(new ToDo(argument));
        }

        case "deadline" -> {
            String[] parsedForBy = Parser.parseArgumentByKeyword(argument, "by");
            String description = parsedForBy[0];
            if (parsedForBy.length == 1) {
                throw new MissingArgumentException("deadline", "/by",
                        "deadline [task description] /by [deadline]");
            }
            String by = parsedForBy[1];
            taskList.addTask(new Deadline(description, convertStringToDateTime(by)));
        }

        case "event" -> {
            String[] parsedForFrom = Parser.parseArgumentByKeyword(argument, "from");
            String description = parsedForFrom[0];

            if (parsedForFrom.length == 1) {
                throw new MissingArgumentException("event", "/from",
                        "event [task description] /from [start time] /to [end time]");
            }

            String[] parsedForTo = Parser.parseArgumentByKeyword(parsedForFrom[1], "to");
            String from = parsedForTo[0];

            if (parsedForTo.length == 1) {
                throw new MissingArgumentException("event", "/to",
                        "event [task description] /from [start time] /to [end time]");
            }

            String to = parsedForTo[1];
            taskList.addTask(new Event(description, convertStringToDateTime(from), convertStringToDateTime(to)));
        }

        default -> throw new InvalidCommandException(command);
        }

        Storage.addTaskToSavedTasks(Casper.FILE_PATH, taskList.getTaskSaveString(taskList.size() - 1),
                taskList.size() == 1);
        return Stringifier.getAddedTaskMessage(taskList.getTaskString(taskList.size() - 1), taskList.size());
    }

}
//CHECKSTYLE.ON: Regexp
