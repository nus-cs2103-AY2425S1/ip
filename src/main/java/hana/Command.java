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

    /**
     * Determines if the command is an exit command.
     * Subclasses can override this method to indicate that they are exit commands.
     *
     * @return {@code true} if the command is an exit command; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
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
        if (index < 0 || index > tasks.size()) {
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
        if (index < 0 || index > tasks.size()) {
            throw new HanaException("Task number out of range.");
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        storage.save(tasks.getTasks());
        ui.showTaskMarked(task);
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

