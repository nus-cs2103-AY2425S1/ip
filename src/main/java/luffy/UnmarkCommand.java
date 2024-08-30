package luffy;

import java.io.IOException;

public class UnmarkCommand extends Command {

    int markIndex;

    public UnmarkCommand(int index) {
        this.markIndex = index;
    }

    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {

        if (markIndex >= 0 && markIndex < taskList.size()) {
            Task task = taskList.getTask(markIndex);
            task.markUndone();
            try {
                taskStorage.saveToFile(taskList);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            ui.showUnmarkedTask(task);
        } else {
            ui.showErrorMessage("Invalid task number.");
        }
    }

}

