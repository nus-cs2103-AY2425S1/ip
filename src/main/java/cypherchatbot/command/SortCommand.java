package cypherchatbot.command;

import java.util.ArrayList;

import cypherchatbot.CypherException;
import cypherchatbot.task.Task;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;



public class SortCommand extends Command {

    private final Integer sortBy;

    private enum SortCommands {
        ASCENDING, DESCENDING
    }
    public SortCommand(String[] command) throws CypherException {
        if (command.length != 2) {
            throw new CypherException(String.format("\"%s\" is not a valid sort command. "
                    + "Sort command format is:\n"
                    + " > sort ascending (earliest task at the top)\n "
                    + " > sort descending (latest task at the top)", command[0]));
        }
        try {
            switch (SortCommands.valueOf(command[1].toUpperCase())) {
            case ASCENDING:
                this.sortBy = 0;
                break;
            case DESCENDING:
                this.sortBy = 1;
                break;
            default:
                throw new CypherException(String.format("\"%s\" is not a valid sort command. "
                        + "Sort command format is:\n"
                        + "  -> sort ascending (earliest task at the top)\n"
                        + "  -> sort descending (latest task at the top)", command[0]));
            }
        } catch (IllegalArgumentException exp) {
            throw new CypherException(String.format("\"%s\" is not a valid sort command. "
                    + "Sort command format is:\n"
                    + "  -> sort ascending (earliest task at the top)\n"
                    + "  -> sort descending (latest task at the top)", String.join(" ", command)));
        }
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CypherException {
        ArrayList<Task> sortedList;
        assert this.sortBy == 1 || this.sortBy == 0 : "Sort By value is invalid";
        if (this.sortBy == 0) {
            sortedList = tasks.sortAscending();
            return ui.showAscendingMessage(sortedList);
        } else {
            sortedList = tasks.sortDescending();
            return ui.showDescendingMessage(sortedList);
        }


    }
    @Override
    public boolean showExitStatus() {
        return false;
    }
}
