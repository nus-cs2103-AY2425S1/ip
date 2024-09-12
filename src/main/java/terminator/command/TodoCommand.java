package terminator.command;

import java.util.ArrayList;

import terminator.task.Task;
import terminator.task.TodoTask;

/**
 * Concrete class representing a command to create a TodoTask.
 */
public class TodoCommand extends Command {

    private static final String ERR_MSG = """
            Todo description cannot be empty.\n
            Usage: todo <description>""";

    public TodoCommand(String input) {
        super(input);
    }

    /**
     * Creates a new TodoTask with the given input and adds it to the task list.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the description of the TodoTask is empty.
     */
    @Override
    public String execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) {
            throw new TerminatorException(ERR_MSG);
        }
        String description = input.trim();
        if (description.isEmpty()) {
            throw new TerminatorException(ERR_MSG);
        }
        Task t = new TodoTask(description);
        todoList.add(t);
        return "Mission parameters updated. Added new objective:\n\n" + t;
    }
}
