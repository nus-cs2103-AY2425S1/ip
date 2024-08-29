package bob.commands;

import java.io.IOException;

import bob.storage.FileWriting;
import bob.storage.Storage;
import bob.data.TaskList;
import bob.ui.Ui;

public class Bye extends Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            FileWriting.saveTasks(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
