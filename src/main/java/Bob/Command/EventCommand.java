package bob.command;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.tasks.Event;
import bob.tasks.Task;
import bob.ui.Ui;

import java.util.ArrayList;

public class EventCommand extends Command {

    private final String taskDescription;

    public EventCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskDescription.isEmpty()) {
            throw new BobException("Description of the event is missing :(");
        } else if (!taskDescription.contains(" /from ") || !taskDescription.contains(" /to ")) {
            throw new BobException("Missing details :(\nPlease use this format: event [description] /from [start] /to [end]");
        }
        String[] eventParts = taskDescription.split(" /from | /to ");
        Task event = new Event(eventParts[0], eventParts[1], eventParts[2]);
        tasks.add(event);
        storage.save(tasks);
        return ui.showAddedTask(event, tasks.size());
    }
}
