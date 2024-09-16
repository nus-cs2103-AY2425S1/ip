package nebula.command;

import nebula.ui.Parser;
import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {

    /**
     * Constructs a MarkCommand object with the specified description.
     *
     * @param description The command description containing the task number to mark.
     */
    public MarkCommand(String description) {
        super(description);
    }

    /**
     * Executes the mark command on the specified task list.
     * Marks the task at the task number parsed from the command as done.
     *
     * @param tasks   The task list where the task will be marked.
     * @param ui      The UI component for user interaction.
     * @param storage The storage component for saving the task list after marking the task.
     * @throws IOException If an I/O error occurs when saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String command = getDescription();

        int taskNum = Parser.splitCommandAndTaskNumber(command);
        return tasks.markTask(taskNum);
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since the mark command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
