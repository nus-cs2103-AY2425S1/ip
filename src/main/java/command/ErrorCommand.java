package command;

import task.TaskList;

public class ErrorCommand extends Command {
    private String errorMsg;

    public ErrorCommand(String e) {
        super(0, null);
        this.errorMsg = e;
    }

    public String execute(TaskList tasks) {
        return this.errorMsg;
    }
}
