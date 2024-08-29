package task;

import exceptions.AlreadyCompletedException;

public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    public static ToDo of(String[] args) throws AlreadyCompletedException {
        ToDo todo = new ToDo(args[2]);
        if (Boolean.parseBoolean(args[1])) {
            todo.complete();
        }
        return todo;
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}
