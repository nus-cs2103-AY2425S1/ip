package Bob.Command;

import Bob.Exception.BobException;
import java.util.ArrayList;
import Bob.Tasks.Task;
import Bob.Tasks.Event;
import Bob.Storage.Storage;
import Bob.Ui.Ui;

public class EventCommand extends Command {
    private final String taskDescription;

    public EventCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskDescription.isEmpty()) {
            throw new BobException("Description of the event is missing :(");
        } else if (!taskDescription.contains(" /from ") || !taskDescription.contains(" /to ")) {
            throw new BobException("Missing details :(\nPlease use this format: event [description] /from [start] /to [end]");
        }
        String[] eventParts = taskDescription.split(" /from | /to ");
        Task event = new Event(eventParts[0], eventParts[1], eventParts[2]);
        tasks.add(event);
        storage.save(tasks);
        ui.showAddedTask(event, tasks.size());
    }
}
