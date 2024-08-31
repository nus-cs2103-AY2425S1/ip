package michaelscott.command;

import michaelscott.task.Task;
import michaelscott.task.TaskList;
import michaelscott.task.Todo;
import michaelscott.utils.MichaelScottException;

/**
 * Represents a command to add a new todoTask to the task list.
 * This command parses the user input to
 * create a new TodoTask with a description and a due date.
 */
public class TodoCommand implements Command {
    private final String description;

    /**
     * Constructs a new TodoCommand by parsing the given arguments.
     *
     * @param args The command arguments containing the task description and deadline.
     *             Expected format: "todo somethingsomthing"
     * @throws MichaelScottException If the input format is invalid or the date cannot be parsed.
     */
    public TodoCommand(String args) throws MichaelScottException {
        this.description = args.trim();
        if (description.isEmpty()) {
            throw new MichaelScottException("The description of a todo cannot be empty. [todo <Task Description>]");
        }
    }

    /**
     * Executes the command by adding a new Todo_ task to the task list.
     *
     * @param tasks The TaskList to which the new Deadline task will be added.
     * @return A string confirming the addition of the task and showing the updated task count.
     */
    @Override
    public String execute(TaskList tasks) {
        Task newTask = new Todo(description);
        tasks.addTask(newTask);
        return "Got it. I've added this task:\n" + newTask.toString()
                + "\nNow you have " + tasks.size()
                + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }
}
