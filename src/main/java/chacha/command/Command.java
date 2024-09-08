package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.task.TaskList;

/**
 * Represents an abstract command for the ChaCha application.
 */
public abstract class Command {
    protected ChaCha chacha;

    public Command(ChaCha chacha) {
        this.chacha = chacha;
    }

    public abstract String execute(String userInput, Storage storage, Ui ui, TaskList tasks);
}
