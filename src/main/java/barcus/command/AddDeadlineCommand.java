package barcus.command;

import java.time.format.DateTimeParseException;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.task.Deadline;
import barcus.task.Task;
import barcus.tasklist.TaskList;

/**
 * Command to ass new deadline task
 */
public class AddDeadlineCommand extends AddCommand {
    protected String by;

    /**
     * Constructor
     * @param description String
     * @param by Date
     */
    public AddDeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }
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

    @Override
    public boolean isExit() {
        return false;
    }

}
