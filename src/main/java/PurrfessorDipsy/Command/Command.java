package PurrfessorDipsy.Command;

import PurrfessorDipsy.Exception.InvalidCommandException;
import PurrfessorDipsy.Exception.InvalidDateException;
import PurrfessorDipsy.TaskList.TaskList;
import PurrfessorDipsy.Ui.Ui;

public class Command {
    protected final String userInput;
    protected final TaskList tasks;
    protected final Ui ui;

    public Command(String userInput, TaskList tasks, Ui ui) {
        this.userInput = userInput;
        this.tasks = tasks;
        this.ui = ui;
    }

    public void execute() throws InvalidCommandException, InvalidDateException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    protected void saveTasksToLocalDisk() {
        tasks.saveToLocalDisk();
    }
}
