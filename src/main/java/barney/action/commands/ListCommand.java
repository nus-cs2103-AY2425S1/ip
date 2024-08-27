package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.ui.Ui;

public class ListCommand extends Command {
    HashMap<String, String> argumentMap;

    public ListCommand(HashMap<String, String> argumentMap) {
        super("list");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        ui.printList(tasks);
        return true;
    }
}
