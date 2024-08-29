package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String byeMessage = "\nEl Primo:\n" +
                "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
    }
}
