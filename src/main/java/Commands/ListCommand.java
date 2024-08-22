package Commands;

import Tasks.TaskManager;

public class ListCommand extends Command {
    private final TaskManager manager;
    public ListCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String userInput) {
        manager.listTasks();
    }
}
