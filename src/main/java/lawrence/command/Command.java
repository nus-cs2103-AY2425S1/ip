package lawrence.command;

import lawrence.database.TaskFileManager;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

import java.io.IOException;

public abstract class Command {
    abstract public void execute(TaskList tasks, TaskFileManager manager, UserInterface ui);

    protected void saveTasks(TaskList tasks, TaskFileManager manager) throws IOException {
        manager.saveTasksToFile(tasks.getTasks());
    }

    public boolean shouldContinue() {
        return true;
    }
}
