package pixel.command;

import pixel.Storage;
import pixel.PixelException;
import pixel.Ui;
import pixel.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        String[] outputs = new String[taskList.size() + 1];
        outputs[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            outputs[i + 1] = index + ". " + taskList.getTaskAtIndex(i);
        }
        ui.PixelSays(outputs);
    }
}
