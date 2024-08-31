package luffy;

import java.io.IOException;

/**
 * Represents a command that searches and displays all tasks
 * with the matching keyword in its representation
 */
public class FindCommand extends Command {

    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {

        TaskList tempList = new TaskList();
        for (Task existingTask : taskList.getTasks()) {
            String taskInfo = existingTask.getTaskInfo();
            if (taskInfo.contains(this.keyword)) {
                tempList.addTask(existingTask);
            }
        }
        ui.showMatchingMessage();
        ui.displayTasks(tempList);
        ui.showLine();
    }
}
