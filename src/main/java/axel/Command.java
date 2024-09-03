package axel;

import java.io.IOException;
import java.util.List;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws AxelException;
    boolean isExit();
}

abstract class CommandBase implements Command {
    @Override
    public boolean isExit() {
        return false;
    }
}

class AddCommand extends CommandBase {
    protected Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AxelException {
        taskList.addTask(task);
        ui.printTaskAdded(task, taskList.size());
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new AxelException("Error saving tasks.");
        }
    }
}

class MarkCommand extends CommandBase {
    protected int taskIndex;
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AxelException {
        Task task = taskList.getTask(taskIndex);
        task.markAsDone();
        ui.printTaskDone(task);
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new AxelException("Error saving tasks.");
        }
    }
}

class UnmarkCommand extends CommandBase {
    protected int taskIndex;
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AxelException {
        Task task = taskList.getTask(taskIndex);
        task.markAsNotDone();
        ui.printTaskNotDone(task);
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new AxelException("Error saving tasks.");
        }
    }
}

class DeleteCommand extends CommandBase {
    protected int taskIndex;
    public DeleteCommand(int taskIndex) {

        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AxelException {
        Task task = taskList.getTask(taskIndex);
        taskList.removeTask(taskIndex);
        ui.printTaskRemoved(task, taskList.size());
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new AxelException("Error saving tasks.");
        }
    }
}

class ListCommand extends CommandBase {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList.getTasks());
    }
}

class ExitCommand extends CommandBase {
    @Override
    public boolean isExit() {
        return true;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // No action needed for exit command
    }
}

class FindCommand extends CommandBase {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> matchingTasks = taskList.findTasksWithKeyword(keyword);
        ui.printMatchingTasks(matchingTasks);
    }
}
