package Naega.Command;

import Naega.Command.Command;
import Naega.NaegaException;
import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String userInput) throws NaegaException {
        this.index = parseTaskIndex(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(index).markAsDone();
        ui.showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks.getTask(index));
        ui.showLine();
        storage.save(tasks.getTasks());
    }

    private int parseTaskIndex(String userInput) throws NaegaException {
        try {
            return Integer.parseInt(userInput.substring(5).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new NaegaException("Invalid task number format.");
        }
    }
}
