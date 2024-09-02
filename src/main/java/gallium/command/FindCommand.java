package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;
import gallium.task.Task;

public class FindCommand extends Command {
    private String Message;

    public FindCommand(String Message) {
        this.Message = Message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        StringBuilder tasksStringBuilder = new StringBuilder();
        String keyword = Message.split("find ")[1];
        for (int i = 1; i < Task.count; i++) {
            Task task = taskList.get(i - 1);
            if (task.getDesc().contains(keyword)) {
                tasksStringBuilder.append("\n    " + task.toString());
            }
        }
        String tasks = tasksStringBuilder.toString();
        ui.printMatchingFind(tasks);
    }
}
