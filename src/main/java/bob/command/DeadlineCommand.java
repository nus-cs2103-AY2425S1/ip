package bob.command;

import bob.Bob;
import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingArgumentException;
import bob.task.Deadline;
import bob.task.Task;

public class DeadlineCommand extends Command {

    public static final String COMMAND = "deadline";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String argument) {
        int byIndex = argument.lastIndexOf("/by ");
        if (byIndex == -1) {
            throw new MissingArgumentException("'by' argument to add a deadline");
        }

        String desc = argument.substring(0, byIndex).strip();
        if (desc.isBlank()) {
            throw new MissingArgumentException("description of the deadline");
        }

        String by = argument.substring(byIndex + 4).strip();

        Task task = new Deadline(
                desc,
                Bob.parseDateTime(by)
        );
        tasks.add(task);
        ui.printWithFormat("added: " + task);
    }
}
