package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;
import calebyyy.Tasks.Deadline;
import calebyyy.Tasks.Event;
import calebyyy.Tasks.Task;
import calebyyy.Tasks.Todo;
import calebyyy.exceptions.InvalidArgumentException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand.
     *
     * @param calebyyy The main Calebyyy object.
     * @param ui The Ui object responsible for user interaction.
     * @param taskList The TaskList object that stores the task list.
     */
    public AddCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    /**
     * Adds a task to the task list.
     *
     * @param input The user input.
     * @throws InvalidArgumentException If the user input is invalid.
     */
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