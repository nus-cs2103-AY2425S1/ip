package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.ui.Ui;

public class FindCommand extends Command {
    HashMap<String, String> argumentMap;

    public FindCommand(HashMap<String, String> argumentMap) {
        super("find");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String keyword = argumentMap.get("keyword");

        TaskList matchingTasks = tasks.find(keyword);
        ui.printMatchingTasks(matchingTasks);

        return true;
    }
}
