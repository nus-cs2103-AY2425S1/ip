package julie.command;

import julie.exception.JulieException;
import julie.misc.UI;
import julie.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    public FindCommand(String commandString) {
        super(commandString);
    }

    @Override
    public void run(List<Task> taskList) throws JulieException {
        List<Task> searchList = new ArrayList<>();
        String searchString = this.commandString.substring(5).toLowerCase();
        for (Task t : taskList) {
            if (t.taskString.toLowerCase().contains(searchString)) {
                searchList.add(t);
            }
        }
        if (searchList.isEmpty()) {
            UI.wrappedLinePrint("Oh no! We could not find anything that matches!");
        } else {
            UI.printList(searchList);
        }
    }
}
