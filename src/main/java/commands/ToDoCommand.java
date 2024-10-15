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
        assert tl != null : "Task list must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert details != null : "Details must not be null";

        Task newTask = new ToDo(details[1]);
        tl.addTask(newTask, storage);
        ui.setResponse("Got it I've added this task:", Utility.INDENT + newTask.toString(),
                String.format("You now have %d tasks in your list.", tl.size()));
        ui.printResponse();
    }
}
