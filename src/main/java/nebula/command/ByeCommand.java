package nebula.command;

import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand object with the specified description.
     *
     * @param description The command description (not used in this command).
     */
    public ByeCommand(String description) {
        super(description);
    }

    /**
     * Executes the bye command.
     * Displays a goodbye message and saves the current task list to a text file.
     *
     * @param tasks   The task list to be saved (not used directly in this method).
     * @param ui      The UI component to display the goodbye message.
     * @param storage The storage component used to save the task list to a file.
     * @throws IOException If an I/O error occurs during saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.saveTaskListToTextFile(TaskList.getTaskList());
        return ui.goodbye();
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return true since the bye command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
