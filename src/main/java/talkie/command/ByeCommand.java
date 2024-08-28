package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.task.TaskList;

import java.io.IOException;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveData(tasks);
            ui.byeMessage();
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong when saving the data!");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
