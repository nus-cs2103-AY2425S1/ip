package terminator.command;

import terminator.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    private static final String ERR_MSG = """
            Index to delete cannot be empty.
            
            Usage: delete <index>""";
    @Override
    public void execute(ArrayList<Task> todoList) throws DukeException {
        if (input == null) {
            throw new DukeException(ERR_MSG);
        }
        try {
            int idx = Integer.parseInt(input.trim()) - 1;

            if (idx >= 0 && idx < todoList.size()) {
                System.out.println("Successfully wiped all traces of the task from the database.");
                Task currentTask = todoList.remove(idx);
                System.out.println(currentTask);
            } else {
                throw new DukeException("Index out of bounds.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error: index is not a valid integer.");
        }
    }
}
