package alexer.command;

import alexer.Alexer;
import alexer.task.TaskManager;
import alexer.task.Todo;
import alexer.ui.Response;

/**
 * A command to create a new to-do task.
 *
 * @author sayomaki
 */
public class AddTodoCommand extends Command {
    public AddTodoCommand() {
        super("todo");
    }

    @Override
    public Response run(String... arguments) {
        TaskManager taskManager = Alexer.getInstance().getTaskManager();
        String description = String.join(" ", arguments);
        if (description.isEmpty()) {
            return new Response("Oh-no! You forgot to include a description for your task!");
        }

        Todo todo = new Todo(description);
        taskManager.addTask(todo);
        taskManager.saveTasks();

        return new Response(String.format(
                "Sure! I’ve added the todo to your list:\n\n\t%s\n\nYou have %d tasks now.",
                todo, taskManager.getTaskCount()));
    }
}
