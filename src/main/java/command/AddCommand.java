package command;

import exceptions.BuddyException;
import storage.Storage;
import task.Deadlines;
import task.Events;
import task.Task;
import task.TaskList;
import task.ToDos;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task (todo, deadline, event) to the task list.
 */
public class AddCommand extends Command {
    private final String command;

    /**
     * Constructs an AddCommand with the specified command string.
     *
     * @param command The command string containing the details of the task to be added.
     */
    public AddCommand(String command) {
        this.command = command;
        assert command != null : "Command cannot be null";
    }

    /**
     * Parses and formats a date string into a LocalDateTime object.
     *
     * @param date The date string in the format "d/M/yyyy HHmm".
     * @return The formatted LocalDateTime object.
     * @throws BuddyException If the date string is not in the correct format.
     */
    private static LocalDateTime formatDate(String date) throws BuddyException {
        assert date != null : "Date cannot be null";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new BuddyException("You need to state the date in the format 'd/M/yyyy HHmm'");
        }
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param tasks   The TaskList object containing the current list of tasks.
     * @param ui      The Ui object for interacting with the user.
     * @param storage The Storage object for saving tasks to the storage file.
     * @throws BuddyException If there is an error in the command, such as missing task descriptions or incorrect date formats.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {

        //Assertions to ensure arguments are valid
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";

        if (command.startsWith("todo")) {
            return addTodoTask(tasks, ui, storage);
        } else if (command.startsWith("deadline")) {
            return addDeadlineTask(tasks, ui, storage);
        } else if (command.startsWith("event")) {
            return addEventTask(tasks, ui, storage);
        } else {
            throw new BuddyException("Unknown task type.");
        }
    }

    private String addTodoTask(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        String taskDesc = command.substring(4).trim();

        if (taskDesc.isEmpty()) {
            throw new BuddyException("The description of a todo cannot be empty.");
        }

        //create and store task in storage
        Task task = new ToDos(taskDesc);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return ui.displayAddTask(task, tasks);
    }

    private String addDeadlineTask(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        String taskDesc = command.substring(8).trim();

        if (taskDesc.isEmpty()) {
            throw new BuddyException("The description of a deadline cannot be empty.");
        }
        String[] parts = taskDesc.split("/by ", 2);
        assert parts.length == 2 : "Deadline task description format incorrect";

        //format parameters for task creation
        String desc = parts[0].trim();
        String deadline = parts[1].trim();
        LocalDateTime date = formatDate(deadline);

        //create and store task in storage
        Task task = new Deadlines(desc, date);
        tasks.addTask(task);
        storage.save(tasks.getTasks());

        return ui.displayAddTask(task, tasks);
    }

    private String addEventTask(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        String taskDesc = command.substring(5).trim();

        if (taskDesc.isEmpty()) {
            throw new BuddyException("The description of an event cannot be empty.");
        }
        String[] parts = taskDesc.split("/from ", 2);
        assert parts.length == 2 : "Event task description format incorrect";

        //format parameters for task creation
        String task = parts[0].trim();
        String dateTimeRange = parts[1].trim();
        String[] dateTimeParts = dateTimeRange.split("/to ", 2);

        assert dateTimeParts.length == 2 : "Event time format incorrect";
        LocalDateTime startTime = formatDate(dateTimeParts[0].trim());
        LocalDateTime endTime = formatDate(dateTimeParts[1].trim());

        //create and store event in storage
        Task eventTask = new Events(task, startTime, endTime);
        tasks.addTask(eventTask);
        storage.save(tasks.getTasks());

        return ui.displayAddTask(eventTask, tasks);
    }
}