package command;

import main.TaskList;
import main.Storage;
import main.Ui;

/**
 * Abstract class for commands
 */
public abstract class Command {

    private String description;

    public Command(String description) {
        this.description = description;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "";
    }

    public String getDescription() {
        return description;
    }
}
