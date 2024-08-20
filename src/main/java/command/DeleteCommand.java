package command;

import task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final String input;
    public DeleteCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(ArrayList<Task> todoList) throws DukeException {
        try {
            int idx = Integer.parseInt(input.split(" ")[1]) - 1;
            if (idx >= 0 && idx < todoList.size()) {
                System.out.println("Successfully wiped all traces of the task from the database.");
                Task currentTask = todoList.remove(idx);
                System.out.println(currentTask);
            } else {
                throw new DukeException("Index out of bounds.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error in parsing int.");
        }
    }
}
