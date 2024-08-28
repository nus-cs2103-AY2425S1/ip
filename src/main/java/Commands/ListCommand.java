package commands;

import applemazer.*;
import tasks.*;
import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {
        Task task;
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); ++i) {
            task = tasks.get(i);
            System.out.println((i + 1) + "." + task.getStatusIcon() + task);
        }
        System.out.println(); // Leave empty line.
    }

    @Override
    public boolean continueProcessing() {
        return true;
    }
}
