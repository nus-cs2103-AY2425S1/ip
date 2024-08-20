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
    public void execute(ArrayList<Task> todoList) throws DukeException {
        int idx = input.indexOf("/from");
        String dates = input.substring(idx);
        String description = input.substring(5, idx).trim();
        if (description.isEmpty()) {
            throw new DukeException("Event description cannot be empty.");
        }
        int toDateIdx = dates.indexOf("/to");
        String fromDate = dates.substring(6, toDateIdx);
        String toDate = dates.substring(toDateIdx + 4);
        Task t = new EventTask(description, fromDate, toDate);
        todoList.add(t);
        System.out.println("Mission parameters updated. Added new objective: " + t);
        System.out.println(todoList.size() + " objective(s) remaining.");
    }
}
