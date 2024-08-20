package command;

import task.EventTask;
import task.Task;

import java.util.ArrayList;

public class EventCommand extends Command {
    private final String input;
    public EventCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(ArrayList<Task> todoList) {
        int idx = input.indexOf("/from");
        String dates = input.substring(idx);
        String description = input.substring(0, idx);
        int toDateIdx = dates.indexOf("/to");
        String fromDate = dates.substring(6, toDateIdx);
        String toDate = dates.substring(toDateIdx + 4);
        Task t = new EventTask(description, fromDate,  toDate);
        todoList.add(t);
        System.out.println("Mission parameters updated. Added new objective: " + t);
        System.out.println(todoList.size() + " objective(s) remaining.");
    }
}
