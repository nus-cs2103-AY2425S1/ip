package command;

import task.TaskList;

public class ErrorCommand extends Command {
    private String errorMsg;

    public ErrorCommand(String e) {
        super(0, null);
        this.errorMsg = e;
    }

    @Override
    public String execute(TaskList tasks) {
        return this.errorMsg;
    }

    @Override
    public boolean isErrorCommand() {
        return true;
    }
}
