package julie.command;

import java.util.ArrayList;
import java.util.List;

import julie.exception.JulieException;
import julie.misc.Storage;
import julie.misc.UI;
import julie.task.Task;

/**
 * The class that encapsulates a FindCommand.
 */
public class FindCommand extends Command {
    public FindCommand(String commandString) {
        super(commandString);
    }

    @Override
    public String run(List<Task> taskList, Storage storage) throws JulieException {
        List<Task> searchList = new ArrayList<>();
        String searchString = this.commandString.substring(5).toLowerCase();
        for (Task t : taskList) {
            if (t.taskString.toLowerCase().contains(searchString)) {
                searchList.add(t);
            }
        }
        if (searchList.isEmpty()) {
            return "Oh no! We could not find anything that matches!";
        } else {
            return UI.getListString(searchList);
        }
    }
}
