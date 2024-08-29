package hana;

import hana.task.Task;
import hana.task.TaskList;


import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HanaException, IOException;
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

