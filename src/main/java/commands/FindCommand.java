package commands;

import java.util.ArrayList;
import java.util.List;

import exceptions.MizzException;
import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;


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

    // TODO: filter out the by/ from/ to part.
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details)
            throws MizzException {
        assert tl != null : "Task list must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert details != null : "Details must not be null";

        List<String> matched = new ArrayList<>();
        matched.add("Here are the tasks found:");
        for (Task t : tl.toArray()) {
            if (t.toString().indexOf(details[1]) != -1) {
                matched.add(t.toString());
            }
        }
        ui.setResponse(matched.toArray(new String[0]));
        ui.printResponse();
    }
}
