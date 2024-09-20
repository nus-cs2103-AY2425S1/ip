package johncena.commands;

import johncena.tasks.Task;

import java.util.ArrayList;

public class ListCommand implements Command {
    private ArrayList<Task> tasks;

    public ListCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
