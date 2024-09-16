package nebula.command;

import nebula.ui.Parser;
import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {

    /**
     * Constructs an UnmarkCommand object with the specified description.
     *
     * @param description The command description containing the task number to unmark.
     */
    public UnmarkCommand(String description) {
        super(description);
    }

    /**
     * Executes the unmark command on the specified task list.
     * Marks the task at the task number parsed from the command as not done.
     *
     * @param tasks   The task list where the task will be unmarked.
     * @param ui      The UI component for user interaction.
     * @param storage The storage component for saving the task list after unmarking the task.
     * @throws IOException If an I/O error occurs when saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String command = getDescription();

        int taskNum = Parser.splitCommandAndTaskNumber(command);
        return tasks.unmarkTask(taskNum);
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since the unmark command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
