package bobbybot.commands;

import bobbybot.BobbyBotException;
import bobbybot.Storage;
import bobbybot.Task;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

import java.io.IOException;

public class CommandMark extends Command {

    private final int index;
    public CommandMark(String argument) throws BobbyBotException {
        String[] params = argument.split(" ");
        if (params.length != 1) {
            throw new BobbyBotException("Please specify exactly one task number.");
        }
        String indexParam = params[0];
        if (!indexParam.matches("\\d+")) {
            throw new BobbyBotException("Please specify a valid number.");
        }
        this.index = Integer.parseInt(argument);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyBotException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new BobbyBotException("Please specify a task number that is in range.");
        }
        Task task = tasks.markDone(index);
        ui.printInput("Nice! I've marked this task as done:", "\t" + task);
        try {
            storage.saveTasksToFile(tasks.toArray());
        } catch (IOException e) {
            throw new BobbyBotException("Error saving to file.");
        }
    }
}
