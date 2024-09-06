package atlas.commands;

import java.time.LocalDateTime;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Deadline;
import atlas.tasks.TaskList;

/**
 * Creates a deadline when this class is instantiated.
 */
public class DeadlineCommand extends Command {
    private final String name;
    private final LocalDateTime deadline;

    /**
     * Instantiates a deadline command.
     *
     * @param name The name or description of the deadline.
     * @param deadline The date and time to finish this deadline by.
     */
    public DeadlineCommand(String name, LocalDateTime deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * Creates a new deadline and adds it to the task list.
     *
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @return String The message returned to be displayed on the chatbot GUI.
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AtlasException {
        Deadline deadline = new Deadline(this.name, this.deadline);
        tasks.add(deadline);
        storage.save();
        return String.format("Got it. I've added this task:\n\t%s\n Now you have %s tasks in the list.",
                deadline, tasks.size());
    }
}
