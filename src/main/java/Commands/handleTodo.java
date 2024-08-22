package Commands;

import Task.Task;
import Task.ToDo;
import Task.TaskList;
import ui.TextUI;
import exception.CitadelTaskNoInput;


public class handleTodo extends Command {
    public handleTodo(String input, TaskList tasks) {
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
