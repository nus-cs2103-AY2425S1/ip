package wenjieBot.commands;

import wenjieBot.Storage;
import wenjieBot.Ui;
import wenjieBot.TaskList;
import wenjieBot.tasks.Task;
import wenjieBot.exceptions.DukeException;
import wenjieBot.exceptions.NoNumberInputtedException;
import wenjieBot.exceptions.OutOfBoundsException;


import java.util.ArrayList;

public class UnmarkCommand extends Command {

    public UnmarkCommand(boolean isActive, String input) {
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

        taskList.get(taskNo).setStatusIcon(false);

        ui.showLine();
        System.out.println(" OK, I've marked this task as not done yet:\n " + taskList.get(taskNo));
        ui.showLine();
    }
}
