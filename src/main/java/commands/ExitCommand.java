package commands;

import task.TaskList;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList) {
        return "----------------------\n"
                + "Till we meet again, GOODBYE";
    }
}
