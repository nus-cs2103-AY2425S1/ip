package chatbot.command;

import chatbot.exception.InputException;
import chatbot.exception.InvalidArgsException;
import chatbot.logic.TaskList;

public class SortCommand extends Command {
    private TaskList taskList;
    private String arg;

    public SortCommand(TaskList taskList, String arg) {
        this.taskList = taskList;
        this.arg = arg;
    }

    @Override
    public String run() throws InputException {
        if (arg.equals("asc")) {
            return taskList.sort(TaskList.SortOrder.ASC);
        } else if (arg.equals("desc")) {
            return taskList.sort(TaskList.SortOrder.DESC);
        } else {
            throw new InvalidArgsException();
        }
    }
}
