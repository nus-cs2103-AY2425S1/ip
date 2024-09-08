package hinlok.command;

import hinlok.file.TaskFile;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) {
        taskFile.saveTasks(taskList);
        System.out.println("see ya");
        isExit();
    }

    public boolean isExit() {
        return true;
    }
}
