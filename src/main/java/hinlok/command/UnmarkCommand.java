package hinlok.command;

import hinlok.file.TaskFile;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;


public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) {
        taskList.unmarkTask(idx);
    }

}