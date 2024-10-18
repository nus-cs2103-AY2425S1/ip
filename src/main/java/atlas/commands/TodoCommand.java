package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.tasks.Todo;

/**
 * Creates an todo when this class is instantiated.
 */
public class TodoCommand extends Command {
    private final String name;
    public TodoCommand(String name) {
        this.name = name;
    }

    /**
     * Creates a todo and adds it to the task list.
     *
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @return String The message returned to be displayed on the chatbot GUI.
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AtlasException {
        Todo todo = new Todo(this.name);
        tasks.add(todo);
        storage.save();
        return String.format("Got it. I've added this task:\n\t%s\n Now you have %s tasks in the list.",
                todo, tasks.size());
    }
}
