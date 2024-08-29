package src.commands;

import src.*;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    public DeleteCommand(boolean isActive, String input) {
        super(isActive, input);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = getInput().split(" ");
        ArrayList<Task> taskList = tasks.getTasks();

        if (parts.length <= 1) {
            throw new NoNumberInputtedException();
        }

        int taskNo = Integer.parseInt(parts[1]) - 1;

        if (taskNo + 1 > taskList.size()) {
            throw new OutOfBoundsException();
        }

        Task taskToRemove = taskList.get(taskNo);
        taskList.remove(taskNo);

        ui.showLine();
        System.out.println(
                "Noted. I've removed this task:\n" +
                taskToRemove + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.\n");
        ui.showLine();
    }
}
