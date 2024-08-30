package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Deadline;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String name;
    private final LocalDateTime deadline;
    public DeadlineCommand(String name, LocalDateTime deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * @param tasks
     * @param ui
     * @param storage
     * @throws AtlasException
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
