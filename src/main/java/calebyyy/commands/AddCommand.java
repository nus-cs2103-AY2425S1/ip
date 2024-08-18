package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.Tasks.Deadline;
import calebyyy.Tasks.Event;
import calebyyy.Tasks.Task;
import calebyyy.Tasks.Todo;

public class AddCommand extends Command {
    public AddCommand(Calebyyy calebyyy) {
        super(calebyyy);
    }

    @Override
    public void execute(String input) {
        String[] parts = input.split(" ", 2);
        String commandType = parts[0];
        String taskDetails = parts[1];

        Task task;
        if (commandType.equals("todo")) {
            task = new Todo(taskDetails);
        } else if (commandType.equals("deadline")) {
            String[] details = taskDetails.split(" /by ");
            task = new Deadline(details[0], details[1]);
        } else if (commandType.equals("event")) {
            String[] details = taskDetails.split(" /from | /to ");
            task = new Event(details[0], details[1], details[2]);
        } else {
            throw new IllegalArgumentException("Unknown task type");
        }

        calebyyy.addTask(task);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + calebyyy.getTaskCount() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}