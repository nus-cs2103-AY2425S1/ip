package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.tasks.Todo;
import atlas.ui.Ui;

/**
 * Creates an todo when this class is instantiated.
 */
public class TodoCommand extends Command {
    private final String name;
    public TodoCommand(String name) {
        this.name = name;
    }

    /**
     * @param tasks The current list of tasks in the chatbot.
     * @param ui The current ui object the chatbot uses to display messages
     * @param storage The storage object the chatbot uses to store and load tasks
     * @throws AtlasException The exception to be thrown in the event of any error.
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
