package parser;

import exceptions.TheOrangeRatchetCatException;
import tasklist.TaskList;
import tasks.Task;
import java.util.List;
import java.util.Scanner;

public class ToDoCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        try {
            String taskDescription = input.substring(4).trim();
            return TaskList.addingToDo(taskDescription, items, scanner);
        } catch (TheOrangeRatchetCatException e) {
            System.out.println(e.getMessage());
            return scanner.nextLine();
        }
    }
}
