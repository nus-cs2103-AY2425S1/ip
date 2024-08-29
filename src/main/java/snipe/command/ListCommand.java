package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException {
        StringBuilder message = new StringBuilder();
        message.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String item = String.format("%d. %s\n", i + 1, tasks.getTask(i).toString());
            message.append(item);
        }
        String resultMessage = message.toString().stripTrailing();
        ui.printWithLines(resultMessage);
    }
}
