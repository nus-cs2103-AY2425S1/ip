package zaibot.command;

import zaibot.utils.Storage;
import zaibot.utils.TaskList;
import zaibot.utils.Ui;
import zaibot.exception.ZaibotException;

import java.util.HashMap;

/**
 * This class represents the command to find tasks.
 */
public class TaskFindCommand extends Command {
    public TaskFindCommand(String name, HashMap<String, String> optionMap) {
        super(name, optionMap);
    }

    @Override
    public String runCommandSpecificLogic(TaskList tasks, Storage storage) throws ZaibotException {
        HashMap<String, String> optionMap = getOptionMap();

        if (!optionMap.containsKey("name")) {
            throw new ZaibotException("No name given");
        } else {
            String name = optionMap.get("name");
            return Ui.printMessage("FILTER") + "\n" + Ui.printTaskList(tasks.filterTasks(name));
        }
    }
}
