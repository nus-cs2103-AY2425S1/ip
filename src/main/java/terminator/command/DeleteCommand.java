package terminator.command;

import java.util.ArrayList;

import terminator.task.Task;

/**
 * Concrete class representing a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private static final String ERR_MSG = """
            Index to delete cannot be empty.\n
            Usage: delete <index>""";

    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Deletes a task from the given task list using the index specified by the user.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the index is out of bounds.
     */
    @Override
    public String execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) {
            throw new TerminatorException(ERR_MSG);
        }
        String response = "";
        try {
            int idx = Integer.parseInt(input.trim()) - 1;

            if (idx >= 0 && idx < todoList.size()) {
                Task currentTask = todoList.remove(idx);
                response = "Successfully wiped all traces of the task from the database.\n" + currentTask;
            } else {
                throw new TerminatorException("Index out of bounds.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error: index is not a valid integer.");
        }
        return response;
    }
}
