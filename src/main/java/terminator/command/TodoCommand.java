package terminator.command;

import terminator.task.Task;
import terminator.task.TodoTask;

import java.util.ArrayList;

/**
 * Concrete class representing a command to create a TodoTask.
 */
public class TodoCommand extends Command {

    public TodoCommand(String input) {
        super(input);
    }

    private static final String ERR_MSG = """
            Todo description cannot be empty.
            
            Usage: todo <description>""";

    /**
     * Creates a new TodoTask with the given input and adds it to the task list.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the description of the TodoTask is empty.
     */
    @Override
    public void execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) {
            throw new TerminatorException(ERR_MSG);
        }
        String description = input.trim();
        if (description.isEmpty()) {
            throw new TerminatorException(ERR_MSG);
        }
        Task t = new TodoTask(description);
        todoList.add(t);
        System.out.println("Mission parameters updated. Added new objective:\n\n" + t);
    }
}
