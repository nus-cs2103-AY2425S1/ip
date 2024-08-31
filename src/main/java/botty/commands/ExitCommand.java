package botty.commands;

import botty.tasks.TaskManager;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) {
        return "";
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
