package nebula.command;

import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

import java.io.IOException;

public class HelpCommand extends Command{

    /**
     * Constructs a Command object with the specified description.
     *
     * @param description The description of the command.
     */
    public HelpCommand(String description) {
        super(description);
    }

    /**
     * Executes the command to display the list of commands.
     *
     * @param tasks   The list of tasks (not used in this method).
     * @param ui      The UI component that handles displaying the task list.
     * @param storage The storage component (not used in this method).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.displayCommands();
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since the list command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
