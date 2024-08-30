package mahesh.command;

import mahesh.util.TaskList;

public class FindCommand extends Command {
    private TaskList list;
    private String searchTerm;

    public FindCommand(TaskList list, String searchTerm) {
        this.list = list;
        this.searchTerm = searchTerm;
    }

    public void execute() {
        list.findTaskInList(searchTerm);
    }

    public boolean isExit() {
        return false;
    }
}
