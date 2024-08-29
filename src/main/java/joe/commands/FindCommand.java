package joe.commands;

import joe.tasks.TaskList;

public class FindCommand extends Command {

    private final TaskList taskList;
    private final String keyword;

    public FindCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        taskList.findTasks(keyword);
    }
}
