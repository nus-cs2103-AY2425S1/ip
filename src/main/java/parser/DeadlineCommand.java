package parser;

import java.util.List;

import exceptions.TheOrangeRatchetCatException;
import tasklist.TaskList;
import tasks.Task;

/**
 * DeadlineCommand class is a command class that adds a deadline task to the list of tasks.
 */
public class DeadlineCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        try {
            return TaskList.addNewDeadlineTask(input, items);
        } catch (TheOrangeRatchetCatException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
