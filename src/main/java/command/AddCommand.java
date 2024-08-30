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
    }

    /**
     * Parses and formats a date string into a LocalDateTime object.
     *
     * @param date The date string in the format "d/M/yyyy HHmm".
     * @return The formatted LocalDateTime object.
     * @throws BuddyException If the date string is not in the correct format.
     */
    private static LocalDateTime formatDate(String date) throws BuddyException {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        if (command.startsWith("todo")) {
            addTodoTask(tasks, ui, storage);
        } else if (command.startsWith("deadline")) {
            addDeadlineTask(tasks, ui, storage);
        } else if (command.startsWith("event")) {
            addEventTask(tasks, ui, storage);
        } else {
            throw new BuddyException("Unknown task type.");
        }
    }

    private void addTodoTask(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        String taskDesc = command.substring(4).trim();
        if (taskDesc.isEmpty()) {
            throw new BuddyException("The description of a todo cannot be empty.");
        }
        Task task = new ToDos(taskDesc);
        tasks.addTask(task);
        ui.displayAddTask(task, tasks);
        storage.save(tasks.getTasks());
    }

    private void addDeadlineTask(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        String taskDesc = command.substring(8).trim();
        if (taskDesc.isEmpty()) {
            throw new BuddyException("The description of a deadline cannot be empty.");
        }
        String[] parts = taskDesc.split("/by ", 2);

        if (parts.length == 2) {
            String desc = parts[0].trim();
            String day = parts[1].trim();
            LocalDateTime date = formatDate(day);
            Task task = new Deadlines(desc, date);
            tasks.addTask(task);
            ui.displayAddTask(task, tasks);
            storage.save(tasks.getTasks());
        } else {
            throw new BuddyException("When do ya need to get it done by? (Include '/by' after your description followed by the deadline in the format 'd/M/yyyy HHmm')");
        }
    }

    private void addEventTask(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        String taskDesc = command.substring(5).trim();
        if (taskDesc.isEmpty()) {
            throw new BuddyException("The description of an event cannot be empty.");
        }
        String[] parts = taskDesc.split("/from ", 2);

        if (parts.length == 2) {
            String task = parts[0].trim();
            String dateTimeAndEnd = parts[1].trim();
            String[] dateTimeAndEndParts = dateTimeAndEnd.split("/to ", 2);

            if (dateTimeAndEndParts.length == 2) {
                String startTime = dateTimeAndEndParts[0].trim();
                String endTime = dateTimeAndEndParts[1].trim();
                LocalDateTime formattedStartTime = formatDate(startTime);
                LocalDateTime formattedEndTime = formatDate(endTime);
                Task eventTask = new Events(task, formattedStartTime, formattedEndTime);
                tasks.addTask(eventTask);
                ui.displayAddTask(eventTask, tasks);
                storage.save(tasks.getTasks());
            } else {
                throw new BuddyException("There's no end date? (Include '/to' after the start date)");
            }
        } else {
            throw new BuddyException("There's no start date? (Include '/from' after your description)");
        }
    }
}