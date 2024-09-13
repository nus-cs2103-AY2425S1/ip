
package parser;
import java.util.List;
import java.util.Scanner;

import exceptions.TheOrangeRatchetCatException;
import tasklist.TaskList;
import tasks.Task;
/**
 * EventCommand class created to add Events to the list
 */
public class EventCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        try {
            return TaskList.addingEvent(input, items, scanner);
        } catch (TheOrangeRatchetCatException e) {
            System.out.println(e.getMessage());
            //return scanner.nextLine();
            return e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Correct input format for adding event: event <Task> /from <input> /to <input>");
            //return scanner.nextLine();
            return "Correct input format for adding event: event <Task> /from <input> /to <input>";
        }
    }
}
