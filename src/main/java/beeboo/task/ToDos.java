package beeboo.task;

import beeboo.exception.NoDescriptionException;

public class ToDos extends Tasks {
    public ToDos(String description) {
        super(description);
    }


    @Override
    protected String typeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return typeIcon() + super.toString();
    }

    public static ToDos createToDo(String text) throws NoDescriptionException {
        if (text.isEmpty()) {
            throw new NoDescriptionException("No description");
        }
        return new ToDos(text);
    }

    @Override
    public String saveFormat() {
        return "T | " + (super.isDone ? "1 | " : "0 | ") +  description;
    }
}
