
package parser;
import java.util.List;

import exceptions.ErrorMessages;
import exceptions.TheOrangeRatchetCatException;
import tasklist.TaskList;
import tasks.Task;

/**
 * EventCommand class is a command class that adds an event task to the list of tasks.
 */
public class EventCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        try {
            return TaskList.addNewEventTask(input, items);
        } catch (TheOrangeRatchetCatException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ErrorMessages.ARRAY_OUT_OF_BOUNDS);
            return ErrorMessages.ARRAY_OUT_OF_BOUNDS;
        }
    }
}
