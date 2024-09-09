package yihuibot.ui.function;

import yihuibot.exception.parse.MissingDescriptionException;
import yihuibot.executable.AddTask;
import yihuibot.executable.Executable;
import yihuibot.task.Todo;

/**
 * Process the arguments and return a suitable Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class TodoFunction extends Function {
    /**
     * Return a AddTask Executable that adds a new Todo Task. Throws MissingDescriptionException
     * when no description for the Task is provided.
     *
     * @param arguments the list of arguments called with 'todo'.
     * @return a AddTask Executable.
     * @throws MissingDescriptionException when no description is provided for the Todo Task.
     */
    @Override
    public Executable call(String... arguments) throws MissingDescriptionException {
        String sample = "todo read book";

        if (arguments == null || arguments.length < 1) {
            throw new MissingDescriptionException(sample);
        }

        String description = String.join(" ", arguments);
        Todo task = new Todo(description);
        return new AddTask(task);
    }
}
