package Commands;

import Task.Task;
import Task.ToDo;
import Task.TaskList;
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
        System.out.println("Got it! I have added: " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
}
