package command;

/* My import */
import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzException;
import task.Event;
import task.Task;

public class CommandEvent extends Command {
    private String[] params;

    public CommandEvent(String command, String[] params) {
        super(command);
        this.params = params;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        Task temp = new Event(params[0], "E", Task.convertStringToLocalDateTime(params[1]),
                Task.convertStringToLocalDateTime(params[2]), false);

        list.addTask(temp);
        storage.writeOneToFile(temp);
        ui.printTaskAddedWithDivider("E", list.getSize(), temp);
    }
}
