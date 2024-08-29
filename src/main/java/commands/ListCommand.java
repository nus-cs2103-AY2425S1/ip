package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\nEl Primo:");
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> list = tasks.getTasks();
        int len = list.size();
        for (int i = 0; i < len; i++) {
            String output = i + 1 + "." + list.get(i);
            System.out.println(output);
        }
    }
}
