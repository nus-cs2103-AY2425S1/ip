package MichaelScott.Command;

import MichaelScott.Task.TaskList;

public class ExitCommand implements Command {

    @Override
    public String execute(TaskList tasks) {
        return "Catch you on the flipity flip!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
