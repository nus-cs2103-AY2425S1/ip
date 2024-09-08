package hinlok.command;

import hinlok.exceptions.HinlokException;
import hinlok.file.TaskFile;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int taskIndexDelete) {
        this.idx = idx;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) throws HinlokException {
        taskList.deleteTask(idx);

    }
}
