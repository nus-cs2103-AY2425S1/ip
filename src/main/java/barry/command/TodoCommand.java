package barry.command;

/**
 * Represents a command that adds a to-do task to the task list.
 */

import barry.task.Todo;
public class TodoCommand extends AddCommand {

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the to-do task.
     */
   public TodoCommand(String description) {
       super(new Todo(description));
   }
}
