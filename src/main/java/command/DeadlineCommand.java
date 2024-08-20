package command;

import task.DeadlineTask;
import task.Task;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private final String input;
    public DeadlineCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(ArrayList<Task> todoList) {
        String[] tokens = input.split("/by ");
        String description = tokens[0].replaceFirst("deadline", "").trim();
        String deadline = tokens[1];
        Task t = new DeadlineTask(description, deadline);
        todoList.add(t);
        System.out.println("\tMission parameters updated. Added new objective: " + t);
        System.out.println("\t" + todoList.size() + " objective(s) remaining.");
    }
}
