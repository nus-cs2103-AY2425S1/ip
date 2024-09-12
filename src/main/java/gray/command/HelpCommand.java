package gray.command;

import gray.GrayException;
import gray.Tasks;

/**
 * A command that displays the help menu.
 */
public class HelpCommand implements Command {
    @Override
    public String execute(Tasks tasks) throws GrayException {
        return """
                User commands:
                1. `bye`: ends the conversation and exit from the application.
                2. `list`: list all tasks.
                3. `todo <description>`: add a todo task with the specified description.
                4. `deadline <description> /by <date>`: add a deadline task with the specified description and deadline.
                5. `event <description> /from <start date> /to <end date>`: add an event task with the specified description, start date and end date.
                6. `(mark|unmark) <index>`: marks or unmarks a task with the specified index (in the task list) as completed.
                7. `delete <index>`: delete a task with the specified index (in the task list)
                8. `find <query>`: search and list tasks with the specified query contained in description.
                9. `help`: display this help menu.
                """;
    }
}
