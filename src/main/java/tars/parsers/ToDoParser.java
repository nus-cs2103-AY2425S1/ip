package tars.parsers;

import tars.exceptions.TarsException;

import tars.tasks.Task;
import tars.tasks.ToDo;



/**
 * Parses input data to create a {@link ToDo} task.
 *
 * <p>The {@code ToDoParser} class extends the {@link Parser} class and provides
 * specific logic to parse user input and create {@link ToDo} tasks. It ensures that
 * the input format is correct and throws custom exceptions if the input is invalid.</p>
 */
public class ToDoParser extends Parser {

    /**
     * Parses an array of strings containing task information and converts it into a {@link ToDo} task.
     *
     * @param taskInfo An array of strings representing the task details to be parsed. The first element is expected
     *                 to be the command name, and the second element should contain the task name.
     * @return A {@link ToDo} task created from the provided task information.
     * @throws TarsException if the input is invalid or incomplete.
     */
    @Override
    public Task parse(String[] taskInfo) {
        if (taskInfo.length <= 1) {
            throw new TarsException("Add a name to your tasks.ToDo task");
        }

        String name = taskInfo[1].trim();

        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time add a task name to tell me what you're trying to do");
        }

        return new ToDo(name);
    }

}
