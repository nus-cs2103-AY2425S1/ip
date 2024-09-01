package parser;

import exceptions.TheOrangeRatchetCatException;
import tasklist.TaskList;
import tasks.Task;
import java.util.List;
import java.util.Scanner;

public class EventCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        try {
            //String input = scanner.nextLine();
            return TaskList.addingEvent(input, items, scanner);
        } catch (TheOrangeRatchetCatException e) {
            System.out.println(e.getMessage());
            return scanner.nextLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Correct input format for adding event: event <Task> /from <input> /to <input>");
            return scanner.nextLine();
        }
    }
}