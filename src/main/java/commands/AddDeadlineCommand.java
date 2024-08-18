package commands;

import common.Command;
import common.SkibidiException;
import common.Ui;
import storage.TaskStorage;
import storage.Deadline;

public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;

    public AddDeadlineCommand(String input) throws SkibidiException{
        String[] parts = input.split("/by ");
        if (parts.length < 2) {
            throw new SkibidiException("Invalid deadline format. Usage: deadline [description] /by [date]");
        } else {
            this.description = parts[0].substring(9).trim();
            this.deadline = parts[1].trim();
        }
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) throws SkibidiException {
        Deadline deadline = new Deadline(description, this.deadline);
        storage.addTask(deadline);
        ui.printMessage("Got it. I've added this task:\n  " + deadline);
        return true;
    }
}
