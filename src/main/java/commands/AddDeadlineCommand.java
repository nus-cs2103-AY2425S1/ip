package commands;

import common.Command;
import common.Ui;
import storage.TaskStorage;
import storage.Deadline;

public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;

    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        Deadline deadline = new Deadline(description, this.deadline);
        storage.addTask(deadline);
        ui.printMessage("Got it. I've added this task:\n  " + deadline);
        return true;
    }
}
