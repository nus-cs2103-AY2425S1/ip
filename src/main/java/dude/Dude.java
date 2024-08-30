package dude;

import java.time.LocalDateTime;

import dude.exception.DudeDateTimeFormatException;
import dude.exception.DudeException;
import dude.exception.DudeInvalidArgumentException;
import dude.exception.DudeInvalidCommandException;
import dude.exception.DudeNullDateTimeException;
import dude.exception.DudeNullDescriptionException;
import dude.exception.DudeNumberException;
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
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadData());
        ui = new Ui();
    }

    /**
     * Starts the Dude application.
     */
    public void start() {
        isRunning = true;
        ui.showGreet();

        while (isRunning) {
            try {
                readAndReact();
            } catch (DudeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Reads user input and reacts accordingly.
     *
     * @throws DudeException If an error occurs during processing.
     */
    public void readAndReact() throws DudeException {
        String input = ui.readCommand().strip();
        String taskDes = Parser.getDescription(input);

        switch (Parser.getCommand(input)) {
        case HI:
            ui.showGreet();
            break;
        case BYE:
            exit();
            break;
        case LIST:
            ui.showList(taskList);
            break;
        case MARK:
            mark(taskDes);
            break;
        case UNMARK:
            unmark(taskDes);
            break;
        case DELETE:
            delete(taskDes);
            break;
        case TODO:
            addToDo(taskDes);
            break;
        case DEADLINE:
            addDeadline(taskDes);
            break;
        case EVENT:
            addEvent(taskDes);
            break;
        default:
            throw new DudeInvalidCommandException();
        }
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param taskDes The task description.
     * @throws DudeException If an error occurs during processing.
     */
    public void addToDo(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("todo");
        } else {
            Task newTask = new ToDo(taskDes);
            taskList.addTask(newTask);
            ui.showAdd(newTask, taskList);
        }
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param taskDes The task description.
     * @throws DudeException If an error occurs during processing.
     */
    public void addDeadline(String taskDes) throws DudeException {
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
            ui.showAdd(newTask, taskList);
        }
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param taskDes The task description.
     * @throws DudeException If an error occurs during processing.
     */
    public void addEvent(String taskDes) throws DudeException {
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
            ui.showAdd(newTask, taskList);
        }
    }

    /**
     * Marks a task in task list as completed.
     *
     * @param taskDes The task description.
     * @throws DudeException If an error occurs during processing.
     */
    public void mark(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("mark");
        }

        int index = checkAndConvertNumber(taskDes);

        ui.showMark(taskList.markTask(index));
    }

    /**
     * Marks a task in task list as not completed.
     *
     * @param taskDes The task description.
     * @throws DudeException If an error occurs during processing.
     */
    public void unmark(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("unmark");
        }

        int index = checkAndConvertNumber(taskDes);

        ui.showUnmark(taskList.unmarkTask(index));
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskDes The task description.
     * @throws DudeException If an error occurs during processing.
     */
    public void delete(String taskDes) throws DudeException {
        if (taskDes.isEmpty()) {
            throw new DudeNullDescriptionException("delete");
        }

        int index = checkAndConvertNumber(taskDes);

        ui.showDelete(taskList.deleteTask(index), taskList);
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
     * Exits the application and saves data.
     */
    public void exit() {
        this.isRunning = false;
        storage.saveData(taskList);
        ui.closeScanner();
        ui.showBye();
    }

    public static void main(String[] args) {
        Dude dude = new Dude("./data/dude.txt");
        dude.start();
    }
}
