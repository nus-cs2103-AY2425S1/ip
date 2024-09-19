package parser;

import java.util.List;

import exceptions.TheOrangeRatchetCatException;
import tasklist.TaskList;
import tasks.Task;

/**
 * ToDoCommand class is a command class that adds a todo task to the list of tasks.
 */
public class ToDoCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        try {
            String taskDescription = input.substring(4).trim();
            return TaskList.addNewTodoTask(taskDescription, items);
        } catch (TheOrangeRatchetCatException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
