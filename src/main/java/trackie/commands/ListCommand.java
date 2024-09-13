package trackie.commands;

import javax.sound.midi.Track;
import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.tasks.Task;
import trackie.ui.Trackie;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

/**
 * Represents a command to list out all the tasks in the tasklist at a given point in time.
 */
public class ListCommand extends Command {
    StringBuilder listOfTasks = new StringBuilder();
    /**
     * Creates a new ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the list command.
     * This method causes the tasklist to list out all the tasks that are stored in it
     * at the point in time.
     *
     * @param tasklist The TaskList object from which a task will be deleted.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) throws TrackieException {
        if (tasklist.isEmpty()) {
            throw new TrackieException("Nothing to list here =/");
        }

        int index = 1;
        for (Task t : tasklist.getTasks()) {
            listOfTasks.append(String.valueOf(index) + ". " + t.toString() + System.lineSeparator());
            index++;
        }
        return listOfTasks.toString();
    }
}
