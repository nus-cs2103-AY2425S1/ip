package citadel.commands;

import citadel.Task.Task;
import citadel.Task.ToDo;
import citadel.Task.TaskList;
import citadel.ui.TextUI;
import citadel.exception.CitadelTaskNoInput;


public class HandleTodo extends Command {
    public HandleTodo(String input, TaskList tasks) {
        super(input, tasks);
    }

    @Override
    public void run() throws CitadelTaskNoInput {
        Task t;
        String todo = input.substring(5).trim();
        if (todo.isEmpty()) {
            throw new CitadelTaskNoInput();
        }
        t = new ToDo(todo);
        tasks.add(t);
        TextUI.printTask(t, tasks);
    }
}
