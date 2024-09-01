package mummy.command;

import java.util.HashMap;

import mummy.task.Deadline;
import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.ui.Ui;
import mummy.utility.Storage;


public class DeadlineCommand extends Command {

    public DeadlineCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MummyException {
        String description = this.getArgument("description", "");
        String dueBy = this.getArgument("by");

        if (dueBy == null) {
            throw new MummyException("/by is required");
        }

        this.addTask(new Deadline(description, dueBy), taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
