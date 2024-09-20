package dude;

import java.time.LocalDateTime;
import java.util.ArrayList;

import dude.exception.DudeDateTimeFormatException;
import dude.exception.DudeException;
import dude.exception.DudeInvalidArgumentException;
import dude.exception.DudeInvalidCommandException;
import dude.exception.DudeInvalidNameException;
import dude.exception.DudeNullDateTimeException;
import dude.exception.DudeNullDescriptionException;
import dude.exception.DudeNumberException;
import dude.exception.DudeTaskNotFoundException;
import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.TaskList;
import dude.task.ToDo;

/**
 * Represents the main application class for the Dude chatbot system.
 */
public class Dude {
    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private Ui ui;
    private boolean isRunning;

    /**
     * Constructs a Dude object with the specified directory for storing data files.
     *
     * @param filePath The directory where data files are stored. It must not be empty.
     * @throws AssertionError if the filePath is empty.
     */
    public Dude(String filePath) {
        assert !filePath.isEmpty();

        storage = new Storage(filePath);
        parser = new Parser(storage.loadShortcut());
        taskList = new TaskList(storage.loadData());
        ui = new Ui();
        isRunning = true;
    }

    /**
     * Starts the Dude application.
     */
    public void start() {
        isRunning = true;
        ui.displayMessage(ui.showGreet());

        while (isRunning) {
            String input = ui.readCommand().strip();
            ui.displayMessage(readAndReact(input));
        }
    }

    /**
     * Reads user input and reacts accordingly.
     *
     * @param input The user input string.
     * @return The response message based on the processed input.
     */
    public String readAndReact(String input) {
        try {
            String taskDes = Parser.getDescription(input);

            switch (parser.getCommand(input)) {
            case HI:
                return greet();
            case BYE:
                return exit();
            case LIST:
                return ui.showList(taskList);
            case MARK:
                return mark(taskDes);
            case UNMARK:
                return unmark(taskDes);
            case DELETE:
                return delete(taskDes);
            case TODO:
                return addToDo(taskDes);
            case DEADLINE:
                return addDeadline(taskDes);
            case EVENT:
                return addEvent(taskDes);
            case FIND:
                return find(taskDes);
            case DEFINE:
                return define(taskDes);
            case UNDEFINE:
                return undefine(taskDes);
            default:
                throw new DudeInvalidCommandException();
            }
        } catch (DudeException e) {
            return ui.showError(e);
        }
    }

    /**
     * Returns a greeting message.
     *
     *
     * @return A String containing the greeting message.
     * @throws AssertionError if the system is not running.
     */
    public String greet() {
        assert isRunning;
        return ui.showGreet();
    }

    /**
     * Adds a new ToDo task to the task list and return a message indicating the task has been added.
     *
     * @param taskDes The description of the task to be added.
     * @return A confirmation message that the task has been added, including the current task count.
     * @throws AssertionError if the system is not running.
     * @throws DudeNullDescriptionException if the task description is empty.
     * @throws DudeInvalidNameException if the task name is invalid.
     */
    public String addToDo(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("todo");
        }

        String taskName = taskDes.strip();
        if (!isLegalName(taskName)) {
            throw new DudeInvalidNameException();
        }

        Task newTask = new ToDo(taskName);
        taskList.addTask(newTask);

        assert isRunning;
        return ui.showAdd(newTask, taskList);
    }

    /**
     * Adds a new Deadline task to the task list and return a message indicating the task has been added.
     *
     * @param taskDes The description of the task to be added.
     * @return A confirmation message that the task has been added, including the current task count.
     * @throws AssertionError if the system is not running.
     * @throws DudeNullDateTimeException if the date and time is empty
     * @throws DudeNullDescriptionException if the task description is empty.
     * @throws DudeInvalidNameException if the task name is invalid.
     */
    public String addDeadline(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("deadline");
        }

        String[] splitDes = taskDes.split("/", 2);
        if (splitDes.length < 2) {
            throw new DudeNullDateTimeException("deadline");
        }

        String[] splitBy = splitDes[1].split(" ", 2);
        if (!splitBy[0].equals("by")) {
            throw new DudeInvalidArgumentException("deadline", splitBy[0], "by");
        } else if (splitBy.length == 1) {
            throw new DudeNullDateTimeException("deadline");
        }

        LocalDateTime by = Parser.stringToDateTime(splitBy[1].strip());
        String taskName = splitDes[0].strip();
        if (!isLegalName(taskName)) {
            throw new DudeInvalidNameException();
        }

        Task newTask = new Deadline(taskName, by);
        taskList.addTask(newTask);

        assert isRunning;
        return ui.showAdd(newTask, taskList);
    }

    /**
     * Adds a new Event task to the task list and return a message indicating the task has been added.
     *
     * @param taskDes The description of the task to be added.
     * @return A confirmation message that the task has been added, including the current task count.
     * @throws AssertionError if the system is not running.
     * @throws DudeNullDateTimeException if the date and time is empty
     * @throws DudeNullDescriptionException if the task description is empty.
     * @throws DudeInvalidNameException if the task name is invalid.
     */
    public String addEvent(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("event");
        }

        String[] splitDes = taskDes.split("/", 3);
        if (splitDes.length < 3) {
            throw new DudeNullDateTimeException("event");
        }

        String[] splitFrom = splitDes[1].split(" ", 2);
        if (!splitFrom[0].equals("from")) {
            throw new DudeInvalidArgumentException("event", splitFrom[0], "from");
        } else if (splitFrom.length == 1) {
            throw new DudeNullDateTimeException("event");
        }

        String[] splitTo = splitDes[2].split(" ", 2);
        if (!splitTo[0].equals("to")) {
            throw new DudeInvalidArgumentException("event", splitTo[0], "to");
        } else if (splitTo.length == 1) {
            throw new DudeNullDateTimeException("event");
        }

        LocalDateTime from = Parser.stringToDateTime(splitFrom[1].strip());
        LocalDateTime to = Parser.stringToDateTime(splitTo[1].strip());
        if (!from.isBefore(to)) {
            throw new DudeDateTimeFormatException();
        }

        String taskName = splitDes[0].strip();
        if (!isLegalName(taskName)) {
            throw new DudeInvalidNameException();
        }

        Task newTask = new Event(taskName, from, to);
        taskList.addTask(newTask);

        assert isRunning;
        return ui.showAdd(newTask, taskList);
    }

    /**
     * Checks if a task name is valid by ensuring it does not contain illegal characters.
     *
     * @param taskName The task name to validate.
     * @return true if the task name does not contain any illegal characters; false otherwise.
     */
    public boolean isLegalName(String taskName) {
        String[] illegalStrings = {"|", "/"};

        for (String s : illegalStrings) {
            if (taskName.contains(s)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Marks a task in task list as completed and return a message indicating it.
     *
     * @param taskDes The string containing the index of the task to mark as done.
     * @return A message confirming that the task has been marked as done.
     * @throws AssertionError if the system is not running.
     * @throws DudeException If the task description or the number is invalid.
     */
    public String mark(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("mark");
        }

        int index = checkAndConvertNumber(taskDes);

        assert isRunning;
        return ui.showMark(taskList.markTask(index));
    }

    /**
     * Marks a task in task list as not completed and return a message indicating it.
     *
     * @param taskDes The string containing the index of the task to mark as not done.
     * @return A message confirming that the task has been marked as not done.
     * @throws AssertionError if the system is not running.
     * @throws DudeException If the task description or the number is invalid.
     */
    public String unmark(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("unmark");
        }

        int index = checkAndConvertNumber(taskDes);

        assert isRunning;
        return ui.showUnmark(taskList.unmarkTask(index));
    }

    /**
     * Deletes a task in task list and return a message indicating it.
     *
     * @param taskDes The string containing the index of the task to delete.
     * @return A message confirming that the task has been deleted.
     * @throws AssertionError if the system is not running.
     * @throws DudeException If the task description or the number is invalid.
     */
    public String delete(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("delete");
        }

        int index = checkAndConvertNumber(taskDes);

        assert isRunning;
        return ui.showDelete(taskList.deleteTask(index), taskList);
    }

    /**
     * Checks if the input string can be converted to a valid task index number and converts it.
     *
     * @param s The input string representing a task number.
     * @return The integer value of the task index.
     * @throws DudeNumberException If the input string is not a valid number or if the number is out of the valid task
     *     index range.
     */
    public int checkAndConvertNumber(String s) throws DudeNumberException {
        int index;
        try {
            index = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new DudeNumberException(s);
        }

        if (index < 1 || index > taskList.getLength()) {
            throw new DudeNumberException(s);
        }

        return index;
    }

    /**
     * Finds and returns tasks that match the given task description.
     *
     * @param taskDes The string containing the description to search for.
     * @return A message displaying the list of tasks that match the description.
     * @throws AssertionError if the system is not running.
     * @throws DudeNullDescriptionException If the provided task description is empty.
     * @throws DudeTaskNotFoundException If no tasks matching the description are found.
     */
    public String find(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("find");
        }

        ArrayList<Task> filteredList = taskList.findAllTask(taskDes);

        if (filteredList.isEmpty()) {
            throw new DudeTaskNotFoundException();
        }

        assert isRunning;
        return ui.showFind(filteredList);
    }

    /**
     * Defines a new shortcut for a specific command type and return a message indicating successful define.
     *
     * @param taskDes The string containing the shortcut and command type, separated by a space.
     * @return A message confirming the new shortcut definition.
     * @throws AssertionError if the system is not running.
     * @throws DudeException If the task description is empty or input is invalid.
     */
    public String define(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("define");
        }

        String[] splitDes = taskDes.split(" ", 2);
        if (splitDes.length < 2) {
            throw new DudeNullDescriptionException("define");
        }

        CommandType result = parser.defineShortcut(splitDes[0], splitDes[1]);

        assert isRunning;
        return ui.showDefine(splitDes[0], result);
    }

    /**
     * Deletes a previously defined shortcut and return a message indicating successful delete.
     *
     * @param taskDes The string containing the shortcut.
     * @return A message confirming the shortcut deletion.
     * @throws AssertionError if the system is not running.
     * @throws DudeException If the task description is empty or input is invalid.
     */
    public String undefine(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("define");
        }

        parser.deleteShortcut(taskDes);

        assert isRunning;
        return ui.showUndefine(taskDes);
    }

    /**
     * Exits the application and saves data.
     */
    public String exit() {
        isRunning = false;
        storage.saveShortcut(parser);
        storage.saveData(taskList);
        ui.closeScanner();
        return ui.showBye();
    }

    /**
     * Terminates the application and saves the current state. Return a farewell message.
     *
     * @return A farewell message to the user.
     */
    public boolean isRunning() {
        return isRunning;
    }

    public static void main(String[] args) {
        Dude dude = new Dude("./data");
        dude.start();
    }
}
