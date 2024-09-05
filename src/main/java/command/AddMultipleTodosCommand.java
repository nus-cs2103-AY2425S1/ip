package command;

import java.io.IOException;
import java.util.ArrayList;

import exception.ScheduloException;
import task.Task;
import task.TaskList;
import task.Todo;
import util.Storage;

public class AddMultipleTodosCommand extends Command {
    private ArrayList<Task> todos;

    public AddMultipleTodosCommand(String... todos) {
        this.todos = new ArrayList<>();
        for (String name: todos) {
            this.todos.add(new Todo(name)); 
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws ScheduloException, IOException {
        String message = tasks.addMultipleTodos(todos);
        storage.save(tasks);
        return message;
    }
    
}
