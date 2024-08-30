/**
 * The `Parser` class is responsible for interpreting user input and executing
 * the corresponding commands on the task list.
 */
package duke.parsers;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.exceptions.MissingDateException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.TaskNotFoundException;
import duke.exceptions.MissingTaskNameException;
import duke.exceptions.InvalidInputException;
import duke.ui.Ui;

public class Parser {
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a `Parser` object with the specified task list and user interface.
     *
     * @param taskList The `TaskList` object that stores the tasks.
     * @param ui The `Ui` object used to interact with the user.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses and processes the user's input command.
     *
     * @param userInput The input command provided by the user.
     * @return `true` if the "bye" command is given, signaling the end of the session; `false` otherwise.
     * @throws InvalidInputException If the input command is not recognized.
     * @throws MissingTaskNameException If a task name is missing when adding a task.
     * @throws MissingDateException If a date is missing when adding a deadline or event task.
     * @throws TaskNotFoundException If a task is not found when attempting to mark, unmark, or delete it.
     * @throws InvalidDateException If the date format is invalid when adding a deadline or event task.
     */
    public boolean parse(String userInput) throws InvalidInputException,
            MissingTaskNameException, MissingDateException, TaskNotFoundException, InvalidDateException {
        if (userInput.equals("list")) {
            ui.printMessage(taskList.printList());
        } else if (userInput.equals("bye")) {
            return true;
        } else if (userInput.startsWith("mark")) {
            handleMarkTask(userInput, true);
        } else if (userInput.startsWith("unmark")) {
            handleMarkTask(userInput, false);
        } else if (userInput.startsWith("todo")) {
            handleAddTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            handleAddDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            handleAddEvent(userInput);
        } else if (userInput.startsWith("delete")) {
            handleDeleteTask(userInput);
        } else {
            throw new InvalidInputException();
        }
        return false;
    }

    private void handleMarkTask(String message, boolean mark) throws InvalidInputException,
            TaskNotFoundException {
        String[] split = message.split(" ");
        if (split.length > 2) {
            throw new InvalidInputException();
        }
        try {
            if (mark) {
                taskList.markTask(Integer.parseInt(split[1]));
            } else {
                taskList.unmarkTask(Integer.parseInt(split[1]));
            }
        } catch (NumberFormatException e ) {
            ui.printMessage("Invalid number");
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("Index number does not exist");
        }
    }

    private void handleAddTodo(String message) throws MissingTaskNameException {
        String taskName = message.replace("todo", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("todo");
        }
        taskList.addTask(new Todo(taskName));
    }

    private void handleAddDeadline(String message) throws MissingDateException,
            MissingTaskNameException, InvalidDateException {

        String[] parts = message.split(" /by ");
        String taskName = parts[0].replace("deadline", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("deadline");
        }
        if (parts.length != 2) {
            throw new MissingDateException("deadline");
        }
        String by = parts[1].trim();
        taskList.addTask(new Deadline(taskName, by));

    }

    private void handleAddEvent(String message) throws MissingDateException,
            MissingTaskNameException, InvalidDateException {
        String[] parts = message.split(" /from | /to ");
        String taskName = parts[0].replace("event", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("event");
        }
        if (parts.length != 3) {
            throw new MissingDateException("event");
        }
        String from = parts[1].trim();
        String to = parts[2].trim();
        taskList.addTask(new Event(taskName, from, to));
    }

    private void handleDeleteTask(String message) throws InvalidInputException,
            TaskNotFoundException {
        String[] split = message.split(" ");
        if (split.length > 2) {
            throw new InvalidInputException();
        }
        try {
            taskList.deleteTask(Integer.parseInt(split[1]));
        } catch (NumberFormatException e ) {
            ui.printMessage("Invalid number");
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("Index number does not exist");
        }
    }

}
