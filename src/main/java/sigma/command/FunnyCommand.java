package sigma.command;

import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents a command that returns messages when user inputs certain commands.
 */
public class FunnyCommand extends Command {
    public FunnyCommand(String[] commandArray) {
        super(commandArray);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        switch (commandArray[0]) {
        case "sigma":
            return "WHAT THE SIGMA!!!!";
        case "hello": case "hi":
            return "Hello! I'm Sigma, your skibidi rizzler!";
        case "skibidi":
            return "Skibidi wap pap pap";
        default:
            return "What the sigma... for real";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
