package terminator.command;

import java.util.ArrayList;

import terminator.task.Task;

/**
 * Concrete class representing a command to mark a task in the task list.
 */
public class MarkCommand extends Command {

    private static final String ERR_MSG = """
            Index to mark cannot be empty.\n
            Usage: mark <index>""";

    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Marks a task in the given task list as complete.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the user specifies an index out of bounds.
     */
    @Override
    public String execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) {
            throw new TerminatorException(ERR_MSG);
        }
        String trimmedInput = input.trim();
        String response = "";
        try {
            int idx = Integer.parseInt(trimmedInput) - 1;

            if (idx >= 0 && idx < todoList.size()) {
                Task currentTask = todoList.get(idx);
                currentTask.markAsDone();
                response = "Objective marked as completed. Awaiting next directive:\n" + currentTask;
            } else {
                throw new TerminatorException("Index out of bounds.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error: index is not a valid integer.");
        }
        return response;
    }
}
