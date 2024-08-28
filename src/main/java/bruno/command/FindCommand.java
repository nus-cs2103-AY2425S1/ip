package bruno.command;

import bruno.task.TaskList;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(TaskList tasks, String keyword) {
        super(tasks);
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        getTasks().findTask(keyword);
    }
}
