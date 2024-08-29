package joe.commands;

import joe.tasks.TaskList;

public class SaveCommand extends Command {

    private final TaskList taskList;

    public SaveCommand(TaskList tasklist) {
        this.taskList = tasklist;
    }

    @Override
    public void execute() {
        taskList.saveTasks();
    }
}
