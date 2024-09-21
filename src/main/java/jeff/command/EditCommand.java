package jeff.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;
import jeff.task.ToDo;

/**
 * Represents a command to edit an existing task in the task list.
 * Depending on the type of task (ToDoTask, Deadline, Event), the command allows
 * updating the task description, start time, and/or end time.
 */
public class EditCommand extends Command {
    private String args;
    private int index;

    /**
     * Constructs a {@code EditCommand} with the specified arguments.
     *
     * @param args the command arguments. The arguments are variable depending on what the task is.
     * @throws JeffException if {@code args} is empty, does not properly include "/from" and "/to",
     *                       or is otherwise malformed.
     */
    public EditCommand(String args) throws JeffException {
        // guard clause to ensure args not empty
        if (args.isEmpty()) {
            throw new JeffException("Arguments in the format below, they must be\n"
                    + "edit X newDescription newEndDate newStartDate");
        }
        String[] parts = args.split(" ", 2);

        // ensure that the length of parts is at least 2
        if (parts.length != 2) {
            throw new JeffException("Enough arguments, you did not specify!");
        }

        // ensure that the command in the integers place is a number
        if (!Command.isNumeric(parts[0])) {
            throw new JeffException("An integer, you did not specify!");
        }
        this.index = Integer.parseInt(parts[0]) - 1;
        this.args = parts[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        // Need to find what task is at the index
        Task task = tasks.getTask(index);

        // Dynamically determine the task type and call the appropriate edit method
        if (task instanceof ToDo todo) {
            editTask(tasks, ui, storage, todo);
        } else if (task instanceof Deadline deadline) {
            editTask(tasks, ui, storage, deadline);
        } else if (task instanceof Event event) {
            editTask(tasks, ui, storage, event);
        } else {
            throw new JeffException("Unsupported task type!");
        }
    }

    /**
     * Edits a ToDoTask by updating the description.
     *
     * @param tasks   The task list.
     * @param ui      The user interface to display messages.
     * @param storage The storage to update saved tasks.
     * @param task    The ToDoTask to edit.
     */
    public void editTask(TaskList tasks, Ui ui, Storage storage, ToDo task) throws JeffException {
        String[] parsedArgs = args.split(" ", 2);

        if (parsedArgs.length < 1) {
            throw new JeffException("Valid new description, you must provide!");
        }

        String newDescription = parsedArgs[0];
        ToDo updatedTask = new ToDo(newDescription);
        tasks.updateTask(index, updatedTask);
        storage.updateSave(tasks.getTasks());
        ui.showMessage("Task updated: " + updatedTask);
    }

    /**
     * Edits a Deadline task by updating the description and deadline.
     *
     * @param tasks   The task list.
     * @param ui      The user interface to display messages.
     * @param storage The storage to update saved tasks.
     * @param task    The Deadline task to edit.
     */
    public void editTask(TaskList tasks, Ui ui, Storage storage, Deadline task) throws JeffException {
        String[] parsedArgs = args.split("/by", 2);

        if (parsedArgs.length < 2) {
            throw new JeffException("Valid new description and deadline, you must provide!");
        }

        String newDescription = parsedArgs[0].trim();
        LocalDateTime newEndTime;

        try {
            newEndTime = LocalDateTime.parse(parsedArgs[1].trim(), Storage.getDateTimeFormatter());
        } catch (DateTimeParseException e) {
            throw new JeffException("Dates in the format below, they must be: \n" + Storage.getDateFormat());
        }

        Deadline updatedTask = new Deadline(newDescription, newEndTime);
        tasks.updateTask(index, updatedTask);
        storage.updateSave(tasks.getTasks());
        ui.showMessage("Task updated: " + updatedTask);
    }

    /**
     * Edits an Event task by updating the description, start time, and end time.
     *
     * @param tasks   The task list.
     * @param ui      The user interface to display messages.
     * @param storage The storage to update saved tasks.
     * @param task    The Event task to edit.
     */
    public void editTask(TaskList tasks, Ui ui, Storage storage, Event task) throws JeffException {
        String[] parsedArgs = args.split("/from | /to ", 3);

        if (parsedArgs.length < 3) {
            throw new JeffException("Valid new description, start time and end time, you must provide!");
        }

        String newDescription = parsedArgs[0].trim();
        LocalDateTime newStartTime;
        LocalDateTime newEndTime;

        try {
            newStartTime = LocalDateTime.parse(parsedArgs[1].trim(), Storage.getDateTimeFormatter());
            newEndTime = LocalDateTime.parse(parsedArgs[2].trim(), Storage.getDateTimeFormatter());
        } catch (DateTimeParseException e) {
            throw new JeffException("Event dates in the format below, they must be: \n" + Storage.getDateFormat());
        }

        Event updatedTask = new Event(newDescription, newStartTime, newEndTime);
        tasks.updateTask(index, updatedTask);
        storage.updateSave(tasks.getTasks());
        ui.showMessage("Task updated: " + updatedTask);
    }
}
