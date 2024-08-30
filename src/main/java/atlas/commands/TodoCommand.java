package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.tasks.Todo;
import atlas.ui.Ui;

public class TodoCommand extends Command {
    private final String name;
    public TodoCommand(String name) {
        this.name = name;
    }

    /**
     * @param tasks
     * @param ui
     * @param storage
     * @throws AtlasException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException {
        Todo todo = new Todo(this.name);
        tasks.add(todo);
        storage.save();
        String message = String.format("Got it. I've added this task:\n\t%s\n Now you have %s tasks in the list.",
                todo, tasks.size());
        ui.print(message);
    }
}
