package hinlok.command;

import hinlok.file.TaskFile;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;

public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) {
        taskList.markTask(idx);
    }


}
