package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.ToDo;
import duck.storage.Storage;
import duck.ui.Ui;

public class ToDoCommand extends Command {

    public ToDoCommand(String message) {
        super(message);
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        ToDo todo = parseToDo(message);
        tasks.addTask(todo, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private ToDo parseToDo(String input) throws DuckException {
        // Normalize to lowercase and remove the todo keyword
        String description = input.replaceFirst("(?i)^todo\\s*", "").trim();
        if (description.isEmpty()) {
            throw new DuckException("What are you trying \"to do\", mate? " +
                    "Give me a valid description instead of an empty one.\n" +
                    "todo {description}\n");
        }
        return new ToDo(description);
    }


}
