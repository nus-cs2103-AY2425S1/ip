package bob.command;

import bob.Bob;
import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingArgumentException;
import bob.task.Deadline;
import bob.task.Task;

import java.util.Map;

public class DeadlineCommand extends Command {
    public static final String COMMAND = "deadline";

    public DeadlineCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String by = this.arguments.get("by");
        if (by == null) {
            throw new MissingArgumentException("'by' argument to add a deadline");
        }

        String desc = this.arguments.get("");
        if (desc == null || desc.isBlank()) {
            throw new MissingArgumentException("description of the deadline");
        }

        Task task = new Deadline(desc, Bob.parseDateTime(by));
        tasks.add(task);
        ui.printWithFormat("added: " + task);
    }
}
