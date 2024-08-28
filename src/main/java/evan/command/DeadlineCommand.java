package evan.command;

import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;
import evan.task.Deadline;

public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        ui.showSuccess("Got it. I've added this deadline:\n" + deadline);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
