package hana;

import java.io.IOException;
import java.util.List;

import hana.task.Task;
import hana.task.TaskList;

/**
 * Represents an abstract command in the Hana application.
 * Commands define operations that manipulate the task list and interact with the UI and storage.
 */
public abstract class Command {
    /**
     * Executes the command with the provided task list, UI, and storage.
     * Each subclass of Command will provide its own implementation of this method.
     *
     * @param tasks The list of tasks that the command will act upon.
     * @param ui The UI object that manages interactions with the user.
     * @param storage The storage object responsible for saving and loading tasks.
     * @throws HanaException If the command encounters an error related to Hana-specific logic.
     * @throws IOException If an I/O error occurs during command execution (e.g., saving or loading tasks).
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HanaException, IOException;
}

abstract class MassCommand extends Command {
    protected final String keyword;
    protected List<Task> tasksToOperate;
    protected boolean isAwaitingConfirmation = false;

    public MassCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Search for tasks containing the keyword
        tasksToOperate = tasks.findTasks(keyword);
        ui.showFindResults(tasksToOperate);
        // Display found tasks and ask for confirmation
        if (!tasksToOperate.isEmpty()) {
            ui.askConfirmMassOps(getOperationName());
            isAwaitingConfirmation = true;
        }
    }

    /**
     * Confirms the operation (mark, unmark, delete) and processes user input.
     * @param userInput The confirmation input (e.g., "y" to proceed).
     * @param tasks The task list to process.
     * @param ui The UI for feedback.
     * @param storage The storage for saving changes.
     * @throws IOException If saving fails.
     */
    public void confirm(String userInput, TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (userInput.equalsIgnoreCase("y")) {
            // Perform the operation on all tasks and show success message
            for (Task task : tasksToOperate) {
                operateOnTask(task); // Delegate task operation to the subclass
            }
            storage.save(tasks.getTasks()); // Save the changes
            ui.showMassOperationSuccess(getOperationName());
        } else {
            ui.showOperationCancelled();
        }
    }

    /**
     * Get the operation name (e.g., "marked", "unmarked", "deleted").
     * Subclasses must define this.
     */
    protected abstract String getOperationName();

    /**
     * Performs the specific operation (mark, unmark, delete) on a task.
     * Subclasses must define this.
     * @param task The task to operate on.
     */
    protected abstract void operateOnTask(Task task);
}

class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }
}

class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HanaException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new HanaException("Task number out of range.");
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(task, tasks.size());
    }
}

class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> foundTasks = tasks.findTasks(keyword);
        ui.showFindResults(foundTasks);
    }
}

class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HanaException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new HanaException("Task number out of range.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks.getTasks());
        ui.showTaskMarked(task);
    }
}

class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HanaException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new HanaException("Task number out of range.");
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        storage.save(tasks.getTasks());
        ui.showTaskUnmarked(task);
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

/**
 * Command to mass mark tasks based on a keyword.
 */
class MassMarkCommand extends MassCommand {

    public MassMarkCommand(String keyword) {
        super(keyword); // Call the parent constructor
    }

    @Override
    protected String getOperationName() {
        return "mark";
    }

    @Override
    protected void operateOnTask(Task task) {
        task.markAsDone(); // Define the specific operation to mark as done
    }
}

/**
 * Command to mass mark tasks based on a keyword.
 */
class MassUnmarkCommand extends MassCommand {

    public MassUnmarkCommand(String keyword) {
        super(keyword); // Call the parent constructor
    }

    @Override
    protected String getOperationName() {
        return "unmark";
    }

    @Override
    protected void operateOnTask(Task task) {
        task.markAsUndone(); // Define the specific operation to mark as done
    }
}

/**
 * Command to mass mark tasks based on a keyword.
 */
class MassDeleteCommand extends MassCommand {

    public MassDeleteCommand(String keyword) {
        super(keyword); // Call the parent constructor
    }

    @Override
    protected String getOperationName() {
        return "delete";
    }

    @Override
    public void confirm(String userInput, TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (userInput.equalsIgnoreCase("y")) {
            // Collect tasks to be deleted
            List<Task> tasksToDelete = tasksToOperate;

            // Remove them from the task list
            for (Task task : tasksToDelete) {
                tasks.remove(task); // Remove the task directly from TaskList
            }

            // Save the changes
            storage.save(tasks.getTasks());

            // Display success message
            ui.showMassOperationSuccess(getOperationName());
        } else {
            ui.showOperationCancelled();
        }
    }

    @Override
    protected void operateOnTask(Task task) {
        // No specific operation on individual tasks needed here
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}

