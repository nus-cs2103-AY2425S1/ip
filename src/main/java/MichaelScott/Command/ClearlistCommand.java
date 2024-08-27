package MichaelScott.Command;

import MichaelScott.Task.TaskList;

public class ClearlistCommand implements Command {

    @Override
    public String execute(TaskList tasks) {
        tasks.clearList();
        return "Okay, the list has been cleared";
    }
}
