package command;

import task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> todoList) {
        System.out.println("\tListing current mission objectives:");
        for (int i = 0; i < todoList.size(); i++) {
            Task currentTask = todoList.get(i);
            System.out.println("\t" + (i + 1) + "." + currentTask);
        }
    }
    public ListCommand() {
        super();
    }
}
