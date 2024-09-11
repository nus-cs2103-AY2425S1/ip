package Mentos.Commands;

import Mentos.task.TaskList;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        super("invalid");
    }

    @Override
    public String execute(TaskList tasklist) {
        return super.getGui().invalidCommand();
    }
}
