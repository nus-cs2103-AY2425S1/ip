package joe.commands;

/**
 * Represents a command that prints a list of available commands.
 */
public class HelpCommand extends Command {

    /**
     * Prints a list of available commands.
     */
    @Override
    public String execute() {
        return """
               Here are the available commands:
               bye: ends our interaction :-(
               deadline <description> /by <due date/time>: Creates a Deadline task
               delete <idx>: Deletes the task at your chosen index
               event <description> /from <start date/time> /to <end date/time>: Creates an Event task
               find <queryString>: Finds task with descriptions matching your query regex
               list: Displays your current tasks
               mark <idx>: Marks the task at your chosen index
               save : Saves all tasks in your current list to the database that will be automatically loaded during your next session
               todo <description>: Creates a ToDo task
               unmark <idx>: Unmarks the task at your chosen index""";
    }
}
