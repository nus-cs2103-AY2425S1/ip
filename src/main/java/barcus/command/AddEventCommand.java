package barcus.command;

import barcus.tasklist.TaskList;
import barcus.ui.Ui;
import barcus.storage.Storage;
import barcus.exception.BarcusException;
import barcus.task.Task;
import barcus.task.Event;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends AddCommand {
    protected String from;
    protected String to;

    public AddEventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {
        try {
            Task t = new Event(this.description, this.from, this.to);
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
