package axel;

import java.io.IOException;

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
    void execute(TaskList taskList, Ui ui, Storage storage) throws AxelException;
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
<<<<<<< HEAD
    /**
     * Creates an {@code AddCommand} to add the specified task.
     *
     * @param task The task to be added to the task list.
     */
=======

>>>>>>> branch-A-CodingStandard
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
/**
 * Represents a command to mark a task as done in the task list.
 */
class MarkCommand extends CommandBase {
    protected int taskIndex;
<<<<<<< HEAD
    /**
     * Creates a {@code MarkCommand} to mark the task at the specified index as done.
     *
     * @param taskIndex The index of the task to mark as done.
     */
=======

>>>>>>> branch-A-CodingStandard
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
/**
 * Represents a command to unmark a task as done in the task list.
 */
class UnmarkCommand extends CommandBase {
    protected int taskIndex;
<<<<<<< HEAD
    /**
     * Creates an {@code UnmarkCommand} to unmark the task at the specified index as not done.
     *
     * @param taskIndex The index of the task to unmark as not done.
     */
=======

>>>>>>> branch-A-CodingStandard
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
/**
 * Represents a command to delete a task from the task list.
 */
class DeleteCommand extends CommandBase {
    protected int taskIndex;
<<<<<<< HEAD
    /**
     * Creates a {@code DeleteCommand} to delete the task at the specified index.
     *
     * @param taskIndex The index of the task to delete.
     */
=======

>>>>>>> branch-A-CodingStandard
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
/**
 * Represents a command to list all tasks in the task list.
 */
class ListCommand extends CommandBase {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList.getTasks());
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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // No action needed for exit command
    }
}
