package calebyyy.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;
import calebyyy.exceptions.CalebyyyException;
import calebyyy.exceptions.DuplicateTaskException;
import calebyyy.exceptions.InvalidArgumentException;
import calebyyy.exceptions.InvalidDateException;
import calebyyy.tasks.Deadline;
import calebyyy.tasks.Event;
import calebyyy.tasks.Task;
import calebyyy.tasks.Todo;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand.
     *
     * @param calebyyy The main Calebyyy object.
     * @param ui The Ui object responsible for user interaction.
     * @param taskList The TaskList object that stores the task list.
     */
    public AddCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    /**
     * Checks if the date is valid.
     *
     * @param date The date to be checked.
     * @return True if the date is valid, false otherwise.
     */
    private Boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param input The user input.
     * @throws CalebyyyException If the user input is invalid.
     */
    @Override
    public void execute(String input) throws CalebyyyException {
        String[] parts = parseInput(input);
        validateInput(parts);
        Task task = createTask(parts);
        try {
            addTaskToList(task);
            displayAddTaskMessage(task);
        } catch (DuplicateTaskException e) {
            System.out.println(e);
        }
    }
    /**
     * Parses the user input.
     *
     * @param input The user input.
     * @return An array containing the command type and task details.
     */
    private String[] parseInput(String input) {
        return input.split(" ", 2);
    }
    /**
     * Validates the user input.
     *
     * @param parts The array containing the command type and task details.
     * @throws InvalidArgumentException If the user input is invalid.
     */
    private void validateInput(String[] parts) throws InvalidArgumentException {
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new InvalidArgumentException();
        }
    }
    /**
     * Creates a task object.
     *
     * @param parts The array containing the command type and task details.
     * @return The task object.
     * @throws CalebyyyException If the user input is invalid.
     */
    private Task createTask(String[] parts) throws CalebyyyException {
        String commandType = parts[0];
        String taskDetails = parts[1];

        switch (commandType) {
        case "todo":
            return new Todo(taskDetails);
        case "deadline":
            return createDeadline(taskDetails);
        case "event":
            return createEvent(taskDetails);
        default:
            throw new InvalidArgumentException();
        }
    }

    private Deadline createDeadline(String taskDetails) throws CalebyyyException {
        String[] details = taskDetails.split(" /by ");
        if (details.length < 2) {
            throw new InvalidArgumentException();
        }
        if (!isValidDate(details[1])) {
            throw new InvalidDateException();
        }
        return new Deadline(details[0], details[1]);
    }

    private Event createEvent(String taskDetails) throws CalebyyyException {
        String[] details = taskDetails.split(" /from | /to ");
        if (details.length < 3) {
            throw new InvalidArgumentException();
        }
        if (!isValidDate(details[1]) || !isValidDate(details[2])) {
            throw new InvalidDateException();
        }
        return new Event(details[0], details[1], details[2]);
    }

    private void addTaskToList(Task task) throws DuplicateTaskException {
        try {
            taskList.addTask(task);
        } catch (DuplicateTaskException e) {
            throw e;
        }
    }

    private void displayAddTaskMessage(Task task) {
        ui.addTaskMessage(task, taskList.getTaskCount());
    }
}
