package command;

import task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private final String input;
    public MarkCommand(String input) {
        super();
        this.input = input;
    }

    @Override
    public void execute(ArrayList<Task> todoList) throws DukeException {
        try {
            int idx = Integer.parseInt(input.split(" ")[1]) - 1;
            if (idx >= 0 && idx < todoList.size()) {
                System.out.println("Objective marked as completed. Awaiting next directive:");
                Task currentTask = todoList.get(idx);
                currentTask.markAsDone();
                System.out.println(currentTask);
            } else {
                throw new DukeException("Index out of bounds.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error in parsing int.");
        }
    }
}
