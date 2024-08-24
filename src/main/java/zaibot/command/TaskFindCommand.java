package zaibot.command;

import zaibot.Storage;
import zaibot.TaskList;
import zaibot.Ui;
import zaibot.exception.ZaibotException;
import zaibot.task.Task;

import java.util.HashMap;

/**
 * This class represents the command to find tasks.
 */
public class TaskFindCommand extends Command {
    public TaskFindCommand(String name, HashMap<String, String> optionMap) {
        super(name, optionMap);
    }

    @Override
    public void runCommandSpecificLogic(TaskList tasks, Storage storage) throws ZaibotException {
        HashMap<String, String> optionMap = getOptionMap();

        if (!optionMap.containsKey("name")) {
            throw new ZaibotException("No name given");
        } else {
            String name = optionMap.get("name");
            Ui.printMessage("FILTER");
            Ui.printTaskList(tasks.filterTasks(name));
        }
    }
}
