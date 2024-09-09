package dude;

import java.time.LocalDateTime;
import java.util.ArrayList;

import dude.exception.DudeDateTimeFormatException;
import dude.exception.DudeException;
import dude.exception.DudeInvalidArgumentException;
import dude.exception.DudeInvalidCommandException;
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
    private TaskList taskList;
    private Ui ui;
    private boolean isRunning;

    /**
     * Constructs a Dude with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Dude(String filePath) {
        assert !filePath.isEmpty();

        storage = new Storage(filePath);
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

            switch (Parser.getCommand(input)) {
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
            default:
                throw new DudeInvalidCommandException();
            }
        } catch (DudeException e) {
            return ui.showError(e);
        }
    }

    /**
     * Greet the user.
     *
     * @return A greeting message.
     */
    public String greet() {
        assert isRunning;
        return ui.showGreet();
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param taskDes The task description.
     * @return A message indicating the task has been added.
     * @throws DudeException If an error occurs during processing.
     */
    public String addToDo(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("todo");
        } else {
            Task newTask = new ToDo(taskDes);
            taskList.addTask(newTask);

            assert isRunning;
            return ui.showAdd(newTask, taskList);
        }
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param taskDes The task description.
     * @return A message indicating the task has been added.
     * @throws DudeException If an error occurs during processing.
     */
    public String addDeadline(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("deadline");
        } else {
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
            Task newTask = new Deadline(splitDes[0].strip(), by);
            taskList.addTask(newTask);

            assert isRunning;
            return ui.showAdd(newTask, taskList);
        }
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param taskDes The task description.
     * @return A message indicating the task has been added.
     * @throws DudeException If an error occurs during processing.
     */
    public String addEvent(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("event");
        } else {
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

            Task newTask = new Event(splitDes[0].strip(), from, to);
            taskList.addTask(newTask);

            assert isRunning;
            return ui.showAdd(newTask, taskList);
        }
    }

    /**
     * Marks a task in task list as completed.
     *
     * @param taskDes The task description.
     * @return A message indicating the task has been marked as completed.
     * @throws DudeException If an error occurs during processing.
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
     * Marks a task in task list as not completed.
     *
     * @param taskDes The task description.
     * @return A message indicating the task has been marked as not completed.
     * @throws DudeException If an error occurs during processing.
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
     * Deletes a task from the task list.
     *
     * @param taskDes The task description.
     * @return A message indicating the task has been deleted.
     * @throws DudeException If an error occurs during processing.
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
     * Finds tasks in the task list that match the provided description.
     *
     * @param taskDes The task description.
     * @return A message listing the matching tasks.
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
        } else {
            assert isRunning;
            return ui.showFind(filteredList);
        }
    }

    /**
     * Exits the application and saves data.
     */
    public String exit() {
        isRunning = false;
        storage.saveData(taskList);
        ui.closeScanner();
        return ui.showBye();
    }

    /**
     * Checks if the Dude application is currently running.
     *
     * @return True if the Dude application is running, false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }

    public static void main(String[] args) {
        Dude dude = new Dude("./data/dude.txt");
        dude.start();
    }
}
