package chatbot.task;

import chatbot.exception.EmptyDescException;

/** ToDoTask is a subclass of Task and requires no storing of time */
public class ToDoTask extends Task {
    public ToDoTask(String desc) throws EmptyDescException {
        super(desc);
    }

    /** @inheritDoc with 'todo' in front */
    @Override
    public String getOriginalCommand() {
        return "todo " + super.getOriginalCommand();
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
