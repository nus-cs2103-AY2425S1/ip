package barcus.command;

import barcus.tasklist.TaskList;
import barcus.ui.Ui;
import barcus.storage.Storage;
import barcus.exception.BarcusException;
import barcus.task.*;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends AddCommand {
    protected String by;

    public AddDeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {
        try {
            Task t = new Deadline(this.description, this.by);
            tasks.addTask(t);
            ui.talk("Added task: " + t + "\nThere are " + tasks.getLength() + " task(s) in the list.");
        } catch (DateTimeParseException e) {
            throw new BarcusException("please format date as dd/MM/yyyy HH:mm");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
