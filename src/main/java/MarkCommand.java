import java.util.ArrayList;

import luna.task.Task;

public class MarkCommand extends Command {
    private final int taskToMark;

    public MarkCommand(int taskToMark) {
        this.taskToMark = taskToMark;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws LunaException {
        ArrayList<Task> marked = tasks.markTaskAsDone(taskToMark);
        storage.saveTasks(marked);
    }
}

