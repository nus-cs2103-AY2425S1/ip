package commands;

import common.Command;
import common.Ui;
import storage.TaskStorage;
import storage.Deadline;

public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;

    public AddDeadlineCommand(String input) {
        String[] parts = input.split("/by ");
        if (parts.length < 2) {
            // Invalid format, no need to proceed further
            this.description = null;  // Mark as invalid
            this.deadline = null;
        } else {
            this.description = parts[0].substring(9).trim();
            this.deadline = parts[1].trim();
        }
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        if (description == null || deadline == null) {
            ui.printMessage("Invalid deadline format. Usage: deadline [description] /by [date]");
            return true;
        }
        Deadline deadline = new Deadline(description, this.deadline);
        storage.addTask(deadline);
        ui.printMessage("Got it. I've added this task:\n  " + deadline);
        return true;
    }
}
