package barcus.command;

import java.time.format.DateTimeParseException;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.task.Deadline;
import barcus.task.Task;
import barcus.tasklist.TaskList;

/**
 * Command to add new deadline task
 */
public class AddDeadlineCommand extends AddCommand {
    protected String by;

    /**
     * Constructs an AddDeadlineCommand with the specified description and deadline date.
     *
     * @param description the description of the task
     * @param by the deadline date in the format dd/MM/yyyy HH:mm
     */
    public AddDeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Executes the command by adding a deadline task to the task list.
     *
     * @param tasks the task list to add the deadline task to
     * @param storage the storage object to save the task
     * @throws BarcusException if the deadline format is invalid
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        try {
            Task t = new Deadline(this.description, this.by);
            tasks.addTask(t);
            output = "Added task: " + t + "\nThere are " + tasks.getLength() + " task(s) in the list.";
        } catch (DateTimeParseException e) {
            throw new BarcusException("please format date as dd/MM/yyyy HH:mm");
        }
    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as adding a deadline does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
