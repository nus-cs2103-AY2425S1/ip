package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;


public class AddEventCommand extends Command {
    private final Event event;

    public AddEventCommand(String arguments) throws KukiShinobuException {
        //TODO: Check for missing desc, /from or /to
        //TODO: Modify the logic to split based on "/" instead to accommodate flipped order of flags
//        String[] parts = arguments.split("/", 3);


        String[] parts = arguments.split("\\s+/from\\s+|\\s+/to\\s+", 3);
        if (parts.length != 3) {
            throw new KukiShinobuException("Event is missing description, from or to.");
        }

        String taskDescription = parts[0];
        String start = parts[1];
        String end = parts[2];
        this.event = new Event(taskDescription, start, end);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.event);
    }

}
