package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.Tasks.Deadline;
import calebyyy.Tasks.Event;
import calebyyy.Tasks.Task;
import calebyyy.Tasks.Todo;
import calebyyy.exceptions.InvalidArgumentException;
import calebyyy.TaskList;
import calebyyy.Ui;

public class AddCommand extends Command {
    public AddCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    @Override
    public void execute(String input) throws InvalidArgumentException {
        String[] parts = input.split(" ", 2);

        if (parts.length < 2 || parts[1].isBlank()) {
            throw new InvalidArgumentException();
        }
        
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

        taskList.addTask(task);
        ui.addTaskMessage(task, taskList.getTaskCount());
    }
}