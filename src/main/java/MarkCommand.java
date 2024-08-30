import java.io.IOException;

public class MarkCommand extends Command {

    int markIndex;

    public MarkCommand(int index) {
        this.markIndex = index;
    }

    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {

        if (markIndex >= 0 && markIndex < taskList.size()) {
            Task task = taskList.getTask(markIndex);
            task.markDone();
            try {
                taskStorage.saveToFile(taskList);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            ui.showMarkedTask(task);
        } else {
            ui.showErrorMessage("Invalid task number.");
        }
    }

}
