package terminator.command;

import terminator.task.Task;

import java.util.ArrayList;

public class UnmarkCommand extends Command {

    private final String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    private static final String ERR_MSG = """
            Index to unmark cannot be empty.
            
            Usage: unmark <index>""";

    @Override
    public void execute(ArrayList<Task> todoList) throws DukeException {
        if (input == null) throw new DukeException(ERR_MSG);
        String trimmedInput = input.trim();
        try {
            int idx = Integer.parseInt(trimmedInput) - 1;

            if (idx >= 0 && idx < todoList.size()) {
                System.out.println("Objective reopened:");
                Task currentTask = todoList.get(idx);
                currentTask.markAsIncomplete();
                System.out.println(currentTask);
            } else {
                throw new DukeException("Index out of bounds.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error: index is not a valid integer.");
        }
    }
}
