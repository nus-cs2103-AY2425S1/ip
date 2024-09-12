package duck.commands;

import duck.Parser;
import duck.TaskList;

public abstract class RunOnTaskAtIndexCommand extends Command {
    protected TaskList taskList;
    private Parser lineBuffer;

    public RunOnTaskAtIndexCommand(TaskList taskList, Parser lineBuffer) {
        this.taskList = taskList;
        this.lineBuffer = lineBuffer;
    }

    protected abstract String executeOnTask(TaskList taskList, int taskLabel);

    @Override
    public String executeCommand() {
        int taskLabel = lineBuffer.getInt();
        String response = executeOnTask(taskList, taskLabel);
        return response;
    }
}
