package commands;

import tasks.Task;
import tasks.ToDo;
import util.Storage;
import util.TaskList;
import util.Ui;
import util.Utility;

/**
 * Concrete implementation of todo command class.
 */
public class ToDoCommand extends Command {
    public ToDoCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details) {
        Task newTask = new ToDo(details[1]);
        tl.addTask(newTask, storage);
        ui.printResponse("Got it I've added this task:", Utility.INDENT + newTask.toString(),
                String.format("You now have %d tasks in your list.", tl.size()));
    }
}
