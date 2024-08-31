package command;

import task.TaskList;

public class UnknownCommand extends Command {
    String command;

    public UnknownCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList list) {
        him.Ui.sayInvalidCommand(command);
    }
}
