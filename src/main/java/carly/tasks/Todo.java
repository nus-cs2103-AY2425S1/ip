package carly.tasks;

import java.text.MessageFormat;

public class Todo extends Task{
    private static final String TASKTYPE = "T";

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String toString(){
        return MessageFormat.format("[{0}]{1}", TASKTYPE, super.toString());
    }
}
