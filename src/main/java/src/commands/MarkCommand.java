package src.commands;

import src.*;
import src.commands.Command;

import java.util.ArrayList;

public class MarkCommand extends Command {

    public MarkCommand(boolean isActive, String input) {
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

        taskList.get(taskNo).setStatusIcon(true);

        ui.showLine();
        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(taskNo) + "\n" );
        ui.showLine();
    }
}
