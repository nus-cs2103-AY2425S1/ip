package terminator.command;

import java.util.ArrayList;

import terminator.task.Task;

/**
 * Concrete class representing a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    private static final String ERR_MSG = """
            List command takes no arguments.\n
            Usage: list""";
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Lists all the tasks in the task list.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the user supplies extra parameters with the {@code list} command.
     */
    @Override
    public void execute(ArrayList<Task> todoList) throws TerminatorException {
        if (!(input == null)) {
            throw new TerminatorException(ERR_MSG);
        }
        System.out.println("Listing current mission objectives:\n");
        for (int i = 0; i < todoList.size(); i++) {
            Task currentTask = todoList.get(i);
            System.out.println((i + 1) + "." + currentTask);
        }
    }
}
