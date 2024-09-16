package nebula.command;

import nebula.ui.Parser;
import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand object with the specified description.
     *
     * @param description The command description containing the task number to be deleted.
     */
    public DeleteCommand(String description) {
        super(description);
    }

    /**
     * Executes the delete command on the specified task list.
     * This method deletes the task at the task number parsed from the command description.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param ui      The UI component to display the result of the delete operation.
     * @param storage The storage component (not used in this method).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String command = getDescription();

        int taskNum = Parser.splitCommandAndTaskNumber(command);
        return tasks.deleteTask(taskNum);
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since the delete command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
