package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;


public class AddDeadlineCommand extends Command {
    private final Deadline deadline;

    public AddDeadlineCommand(String arguments) throws KukiShinobuException {
        // TODO: Check for missing description or /by
        String[] parts = arguments.split(" /by ", 2);

        // Checks for missing arguments
        if (parts.length != 2) {
            if (!arguments.contains("/by")) {
                throw new KukiShinobuException("You're missing the /by flag and argument!");
            } else {
                throw new KukiShinobuException("Deadline is missing the description!");
            }
        }

        String taskDescription = parts[0];
        String by = parts[1];
        this.deadline = new Deadline(taskDescription, by);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.deadline);
    }

}
