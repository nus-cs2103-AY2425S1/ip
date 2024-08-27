package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.ui.Ui;

public class EndCommand extends Command {
    HashMap<String, String> argumentMap;

    public EndCommand(HashMap<String, String> argumentMap) {
        super("bye");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);
        return false;
    }

}
