package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;
import pikappi.task.DeadlineTask;

/**
 * Represents a command from user to add a deadline task.
 */
public class DeadlineCommand extends Command {
    protected String description;
    protected String by;

    /**
     * Creates a new DeadlineCommand object.
     *
     * @param fullCommand The command string entered by the user without 'deadline' keyword.
     * @throws PikappiException If the command has invalid arguments.
     */
    public DeadlineCommand(String fullCommand) throws PikappiException {
        try {
            String[] splitCommand = fullCommand.split(" /by ");
            this.description = splitCommand[0];
            this.by = splitCommand[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PikappiException("Pika..? When is the deadline?");
        }
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param tasks List of tasks
     * @param ui Ui object to interact with user
     * @param storage Storage object to save and load tasks
     * @throws PikappiException If the task cannot be added to the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException {
        assert tasks != null : "TaskList cannot be null";
        return tasks.addTask(new DeadlineTask(description, by));
    }
}
