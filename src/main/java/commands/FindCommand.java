package commands;

import MizzExceptions.MizzException;
import util.Storage;
import util.TaskList;
import util.Ui;
import java.util.List;
import java.util.ArrayList;
import tasks.Task;

/**
 * Class encapsulating the find command.
 */
public class FindCommand extends Command {
    /**
     * Constructor for a find command.
     * 
     * @param type The commandType.
     */
    public FindCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details)
            throws MizzException {
        List<String> matched = new ArrayList<>();
        matched.add("Here are the tasks found:");
        for (Task t : tl.toArray()) {
            if (t.toString().indexOf(details[1]) != -1) {
                matched.add(t.toString());
            }
        }
        ui.printResponse(matched.toArray(new String[0]));
    }
}
