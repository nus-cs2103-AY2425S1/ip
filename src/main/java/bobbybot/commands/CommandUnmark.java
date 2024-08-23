package bobbybot.commands;

import bobbybot.DukeException;
import bobbybot.Storage;
import bobbybot.Task;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

import java.io.IOException;

public class CommandUnmark extends Command {

    private final int index;
    public CommandUnmark(String argument) throws DukeException {
        String[] params = argument.split(" ");
        if (params.length != 1) {
            throw new DukeException("Please specify exactly one task number.");
        }
        String indexParam = params[0];
        if (!indexParam.matches("\\d+")) {
            throw new DukeException("Please specify a valid number.");
        }
        this.index = Integer.parseInt(argument);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Please specify a task number that is in range.");
        }
        Task task = tasks.markUndone(index);
        ui.printInput("OK, I've marked this task as not done yet:", "\t" + task);
        try {
            storage.saveTasksToFile(tasks.toArray());
        } catch (IOException e) {
            throw new DukeException("Error saving to file.");
        }
    }
}
