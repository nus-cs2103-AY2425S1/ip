package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

import java.util.Map;

public class ListCommand extends Command {

    public static final String COMMAND = "list";

    public ListCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printWithFormat("You have not added any tasks yet.");
        } else {
            ui.printWithFormat(tasks.toString());
        }
    }
}
