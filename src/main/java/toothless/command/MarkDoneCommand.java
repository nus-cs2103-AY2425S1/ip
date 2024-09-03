package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.exceptions.MissingIndexExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.ui.Ui;

public class MarkDoneCommand extends Command {

    private String description;
    private final static String DIVIDER = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";

    public MarkDoneCommand(String description) {
        this.description = description;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if(description.isEmpty()) {
            throw new MissingIndexExceptions("mark", "mark <index>");
        }
        int markIndex = Integer.parseInt(description);
        taskList.markDone(markIndex);
        storage.saveTask(taskList.getList());
    }
}
