package terminator.command;

import terminator.task.Task;

import java.util.ArrayList;

/**
 * Concrete class representing a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    private static final String ERR_MSG = """
            Index to delete cannot be empty.
            
            Usage: delete <index>""";

    /**
     * Deletes a task from the given task list using the index specified by the user.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the index is out of bounds.
     */
    @Override
    public void execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) {
            throw new TerminatorException(ERR_MSG);
        }
        try {
            int idx = Integer.parseInt(input.trim()) - 1;

            if (idx >= 0 && idx < todoList.size()) {
                System.out.println("Successfully wiped all traces of the task from the database.");
                Task currentTask = todoList.remove(idx);
                System.out.println(currentTask);
            } else {
                throw new TerminatorException("Index out of bounds.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error: index is not a valid integer.");
        }
    }
}
