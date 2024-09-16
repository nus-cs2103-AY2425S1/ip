package chatbot.command;

import chatbot.exception.EmptyArgsException;
import chatbot.exception.InputException;
import chatbot.logic.TaskList;

public class MarkCommand extends Command {
    private TaskList taskList;
    private int idx;
    private boolean shouldMark;

    public MarkCommand(TaskList taskList, int num, boolean shouldMark) {
        this.taskList = taskList;
        this.idx = num - 1;
        this.shouldMark = shouldMark;
    }

    @Override
    public String run() throws InputException {
        return this.taskList.mark(this.idx, this.shouldMark);
    }
}
