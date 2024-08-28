package Command;

import Task.TaskList;

import Storage.Storage;

import Ui.Ui;

public class ExitCommand extends Command {
    private boolean isTerminated;
    private Storage storage;
    private TaskList tasks;

    public ExitCommand(Storage storage, TaskList tasks) {
        isTerminated = false;
        this.storage = storage;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        isTerminated = true;
        storage.writeFile(tasks);
        Ui.printFarewell();
    }

    @Override
    public boolean isTerminated() {
        return isTerminated;
    }
}
