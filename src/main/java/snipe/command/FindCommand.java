package snipe.command;

import snipe.core.TaskList;
import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.task.Task;
import snipe.util.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command{
    private final String keyWord;
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException {
        ArrayList<Task> filteredTasks = tasks.findTasks(this.keyWord);
        StringBuilder message = new StringBuilder();
        message.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < filteredTasks.size(); i++) {
            String item = String.format("%d. %s\n", i + 1, filteredTasks.get(i).toString());
            message.append(item);
        }
        String resultMessage = message.toString().stripTrailing();
        ui.printWithLines(resultMessage);
    }
}
