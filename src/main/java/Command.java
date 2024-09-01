import java.io.IOException;

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
    private Task task;

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
    private int taskIndex;

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
    private int taskIndex;

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
    private int taskIndex;

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
