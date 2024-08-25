package terminator.command;

import terminator.task.Task;

import java.util.ArrayList;

/**
 * Concrete class representing a command to unmark an item in the task list.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(String input) {
        super(input);
    }

    private static final String ERR_MSG = """
            Index to unmark cannot be empty.
            
            Usage: unmark <index>""";

    /**
     * Marks a task in the given task list as incomplete.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the user specifies an index out of bounds.
     */
    @Override
    public void execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) throw new TerminatorException(ERR_MSG);
        String trimmedInput = input.trim();
        try {
            int idx = Integer.parseInt(trimmedInput) - 1;

            if (idx >= 0 && idx < todoList.size()) {
                System.out.println("Objective reopened:");
                Task currentTask = todoList.get(idx);
                currentTask.markAsIncomplete();
                System.out.println(currentTask);
            } else {
                throw new TerminatorException("Index out of bounds.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error: index is not a valid integer.");
        }
    }
}
