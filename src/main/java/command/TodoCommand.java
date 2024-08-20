package command;

import task.Task;
import task.TodoTask;

import java.util.ArrayList;

public class TodoCommand extends Command {

    private final String input;
    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(ArrayList<Task> todoList) {
        Task t = new TodoTask(input);
        todoList.add(t);
        System.out.println("Mission parameters updated. Added new objective:\n" + t);
        System.out.println("" + todoList.size() + " objective(s) remaining.");
    }
}
