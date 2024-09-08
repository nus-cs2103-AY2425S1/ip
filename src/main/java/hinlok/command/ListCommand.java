package hinlok.command;

import hinlok.file.TaskFile;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) {
        ui.showTasks(taskList);
        isExit();
    }


}
