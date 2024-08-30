package atlas.commands;

import java.time.LocalDateTime;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Deadline;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

/**
 * Creates an deadline when this class is instantiated.
 */
public class DeadlineCommand extends Command {
    private final String name;
    private final LocalDateTime deadline;
    public DeadlineCommand(String name, LocalDateTime deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * @param tasks The current list of tasks in the chatbot.
     * @param ui The current ui object the chatbot uses to display messages
     * @param storage The storage object the chatbot uses to store and load tasks
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException {
        Deadline deadline = new Deadline(this.name, this.deadline);
        tasks.add(deadline);
        storage.save();
        String message = String.format("Got it. I've added this task:\n\t%s\n Now you have %s tasks in the list.",
                deadline, tasks.size());
        ui.print(message);
    }
}
