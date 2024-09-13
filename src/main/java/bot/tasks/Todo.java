package bot.tasks;

import bot.enums.TaskSymbol;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return TaskSymbol.TODO.name + " | " + super.toData();
    }
}
