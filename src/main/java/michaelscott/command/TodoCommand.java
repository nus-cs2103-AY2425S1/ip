package michaelscott.command;

import michaelscott.exception.MichaelScottException;
import michaelscott.task.Task;
import michaelscott.task.TaskList;
import michaelscott.task.Todo;

public class TodoCommand implements Command {
    private final String description;

    public TodoCommand(String args) throws MichaelScottException {
        this.description = args.trim();
        if (description.isEmpty()) {
            throw new MichaelScottException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String execute(TaskList tasks) {
        Task newTask = new Todo(description);
        tasks.addTask(newTask);
        return "Got it. I've added this task:\n" + newTask.toString() +
                "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }
}
