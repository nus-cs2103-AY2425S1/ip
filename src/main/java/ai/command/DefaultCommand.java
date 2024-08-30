package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;

public class DefaultCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws AiException {
        System.out.println("Sorry, I don't quite understand what you mean..."
                + "wanna try typing smth else??\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
