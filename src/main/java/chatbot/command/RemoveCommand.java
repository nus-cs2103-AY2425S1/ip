package chatbot.command;

import chatbot.exception.InputException;
import chatbot.logic.TaskList;

public class RemoveCommand extends Command {
    private TaskList taskList;
    private int idx;

    public RemoveCommand(TaskList taskList, int num) {
        this.taskList = taskList;
        this.idx = num - 1;
    }

    @Override
    public String run() throws InputException {
        return taskList.remove(idx);
    }
}
