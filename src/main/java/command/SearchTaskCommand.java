package command;

import task.TaskList;

public class SearchTaskCommand extends Command {
    private String searchTerm;

    public SearchTaskCommand(String s) {
        super(0, null);
        this.searchTerm = s;
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.search(this.searchTerm).taskListToString();
    }
}
