package command;

import task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(0, null);
    }

    @Override
    public String execute(TaskList tasks) {
        return "Alright, I've saved your tasks! Goodbye!";
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
