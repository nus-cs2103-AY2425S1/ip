package nebula.command;

import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

import java.io.IOException;

public abstract class Command {
    private String description;

    /**
     * Constructs a Command object with the specified description.
     *
     * @param description The description of the command.
     */
    public Command(String description) {
        this.description = description;
    }

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The list of tasks to be manipulated by the command.
     * @param ui      The UI component to display messages to the user.
     * @param storage The storage to save any changes made to the task list.
     * @throws IOException If an I/O error occurs while executing the command or saving changes.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Indicates whether this command will cause the application to exit.
     *
     * @return true if the application should exit after executing the command and false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Returns the description of this command.
     *
     * @return A string representing the command's description.
     */
    public String getDescription() {
        return description;
    }
}
