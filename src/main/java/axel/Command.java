package axel;

import java.io.IOException;
import java.util.List;

/**
 * Represents a command that can be executed by the application.
 * Each command manipulates the task list, interacts with the user interface,
 * and may involve saving changes to storage.
 */
public interface Command {
    /**
     * Executes the command, performing its associated actions on the task list,
     * user interface, and storage.
     *
     * @param taskList The list of tasks the command will interact with.
     * @param ui       The user interface for displaying information and interacting with the user.
     * @param storage  The storage system for saving tasks.
     * @throws AxelException If an error occurs during command execution.
     */
    String execute(TaskList taskList, Ui ui, Storage storage) throws AxelException;

    /**
     * Determines if the command signals the application to exit.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    boolean isExit();
}

/**
 * An abstract base class for commands.
 * Provides a default implementation for the {@code isExit} method,
 * which returns {@code false} by default.
 */
abstract class CommandBase implements Command {
    @Override
    public boolean isExit() {
        return false;
    }
}

/**
 * Represents a command to add a new task to the task list.
 */
class AddCommand extends CommandBase {
    protected Task task;

    /**
     * Creates an {@code AddCommand} to add the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AxelException {
        assert task != null : "Task to be added cannot be null";
        taskList.addTask(task);
        ui.printTaskAdded(task, taskList.size());
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new AxelException("Error saving tasks.");
        }
        return ui.showTaskAddedAsString(task, taskList.size());
    }
}

/**
 * Represents a command to mark a task as done in the task list.
 */
class MarkCommand extends CommandBase {
    protected int taskIndex;

    /**
     * Creates a {@code MarkCommand} to mark the task at the specified index as done.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AxelException {
        Task task = taskList.getTask(taskIndex);
        task.markAsDone();
        ui.printTaskDone(task);
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new AxelException("Error saving tasks.");
        }
        return ui.showTaskDoneAsString(task);
    }
}
/**
 * Represents a command to unmark a task as done in the task list.
 */
class UnmarkCommand extends CommandBase {
    protected int taskIndex;

    /**
     * Creates an {@code UnmarkCommand} to unmark the task at the specified index as not done.
     *
     * @param taskIndex The index of the task to unmark as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AxelException {
        Task task = taskList.getTask(taskIndex);
        task.markAsNotDone();
        ui.printTaskNotDone(task);
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new AxelException("Error saving tasks.");
        }
        return ui.showTaskNotDoneAsString(task);
    }

}

/**
 * Represents a command to delete a task from the task list.
 */
class DeleteCommand extends CommandBase {
    protected int taskIndex;

    /**
     * Creates a {@code DeleteCommand} to delete the task at the specified index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AxelException {
        Task task = taskList.getTask(taskIndex);
        taskList.removeTask(taskIndex);
        ui.printTaskRemoved(task, taskList.size());
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            throw new AxelException("Error saving tasks.");
        }
        return ui.showTaskRemovedAsString(task, taskList.size());
    }
}

/**
 * Represents a command to list all tasks in the task list.
 */
class ListCommand extends CommandBase {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList.getTasks());
        return ui.showTaskListAsString(taskList.getTasks());
    }
}

/**
 * Represents a command to exit the application.
 */
class ExitCommand extends CommandBase {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
        return ui.showGoodbyeAsString();
    }
}

/**
 * Represents a command to find the corresponding task in the task list.
 */
class FindCommand extends CommandBase {
    protected String keyword;

    public FindCommand(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword for find command should not be null or empty";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> matchingTasks = taskList.findTasksWithKeyword(keyword);
        assert matchingTasks != null : "Matching tasks list should not be null";
        ui.printMatchingTasks(matchingTasks);
        return ui.showMatchingTasksAsString(matchingTasks);
    }
}
