package commands;

import java.util.ArrayList;

import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Concrete implementation of list command class.
 */
public class ListCommand extends Command {
    public ListCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details) {
        int no = 1;
        Task[] tasks = tl.toArray();
        ArrayList<String> taskStrings = new ArrayList<>();
        taskStrings.add("Here are tasks in your list:");
        for (Task t : tasks) {
            taskStrings.add(no + ". " + t.toString());
            no++;
        }
        ui.printResponse(taskStrings.toArray(new String[tasks.length]));
    }
}
