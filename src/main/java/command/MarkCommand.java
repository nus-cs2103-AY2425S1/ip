package command;

import task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private final String input;
    public MarkCommand(String input) {
        super();
        this.input = input;
    }

    private static final String ERR_MSG = """
            Index to mark cannot be empty.
            
            Usage: mark <index>""";

    @Override
    public void execute(ArrayList<Task> todoList) throws DukeException {
        if (input == null) {
            throw new DukeException(ERR_MSG);
        }
        String trimmedInput = input.trim();
        try {
            int idx = Integer.parseInt(trimmedInput) - 1;

            if (idx >= 0 && idx < todoList.size()) {
                System.out.println("Objective marked as completed. Awaiting next directive:");
                Task currentTask = todoList.get(idx);
                currentTask.markAsDone();
                System.out.println(currentTask);
            } else {
                throw new DukeException("Index out of bounds.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error: index is not a valid integer.");
        }
    }
}
