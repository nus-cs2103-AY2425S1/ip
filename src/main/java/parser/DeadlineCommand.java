package parser;

import java.util.List;
import java.util.Scanner;

import exceptions.TheOrangeRatchetCatException;
import tasklist.TaskList;
import tasks.Task;

/**
 * A Deadline class created for adding Deadline tasks
 */
public class DeadlineCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        try {
            return TaskList.addingDeadline(input, items, scanner);
        } catch (TheOrangeRatchetCatException e) {
            System.out.println(e.getMessage());
            return scanner.nextLine();
        }
    }
}
