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
public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toFileText() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

