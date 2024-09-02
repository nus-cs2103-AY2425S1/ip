package chatBot.task;

import chatBot.exception.EmptyDescException;

public class ToDoTask extends Task{
    public ToDoTask(String desc) throws EmptyDescException {
        super(desc);
    }

    @Override
    public String getOriginalCommand() {
        return "todo " + super.getOriginalCommand();
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
