package demurebot.command;

import demurebot.task.TaskList;
import demurebot.task.Todo;
import demurebot.ui.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        super(false);
        this.description = description;
    }

    @Override
    public void execute(TaskList list, Ui ui) {
        Todo todo = new Todo(description, false);
        list.addTask(todo);
        ui.displayAddTask(todo, list.getSize());
    }
}
