package evan.command;

import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        ui.showSuccess("Got it. I've added this deadline:\n" + deadline);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
