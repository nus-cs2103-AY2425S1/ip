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

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.displayCommands();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
