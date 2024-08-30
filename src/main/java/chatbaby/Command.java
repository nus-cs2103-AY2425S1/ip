package chatbaby;

import chatbaby.Command;
import chatbaby.Storage;
import chatbaby.Task;
import chatbaby.TaskType;
import chatbaby.TaskList;
import chatbaby.Ui;
import chatbaby.Parser;
import chatbaby.ChatBabyException;
import chatbaby.Deadline;
import chatbaby.Event;
import chatbaby.ToDo;

public abstract class Command {
    String commandBody;

    public Command(String commandBody) {
        this.commandBody = commandBody;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException;

    public boolean isExit() {
        return false;
    }
}
