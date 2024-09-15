package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;
import pikappi.task.EventTask;

/**
 * Represents a command by user to add an event task.
 */
public class EventCommand extends Command {
    protected String description;
    protected String from;
    protected String to;

    /**
     * Creates a new EventCommand object.
     *
     * @param fullCommand The command string entered by the user without 'event' keyword.
     * @throws PikappiException If the command has invalid arguments.
     */
    public EventCommand(String fullCommand) throws PikappiException {
        try {
            String[] splitCommand = fullCommand.split(" /from ");
            if (splitCommand.length > 2) {
                throw new PikappiException("Pika..? Too many '/from' keywords..");
            }
            this.description = splitCommand[0];
            String[] splitFrom = splitCommand[1].split(" /to ");
            if (splitFrom.length > 2) {
                throw new PikappiException("Pika..? Too many '/to' keywords..");
            }
            this.from = splitFrom[0];
            this.to = splitFrom[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PikappiException("Pika..? What is the event timings?");
        }
    }

    /**
     * Adds an event task to the list of tasks.
     *
     * @param tasks List of tasks
     * @param ui Ui object to interact with user
     * @param storage Storage object to save and load tasks
     * @throws PikappiException If the task cannot be added to the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException {
        assert tasks != null : "TaskList cannot be null";
        return tasks.addTask(new EventTask(description, from, to));
    }
}
