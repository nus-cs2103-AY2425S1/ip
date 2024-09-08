package evan.command;

import evan.service.TaskList;
import evan.task.Deadline;

/**
 * Represents a command that the chatbot can execute to create a Deadline.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Instantiates a DeadlineCommand object.
     *
     * @param description Description of the Deadline to be created.
     * @param by          End date/time of the Deadline to be created.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) {
        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        return "Got it. I've added this deadline:\n" + deadline;
    }
}
