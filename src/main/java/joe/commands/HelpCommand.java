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
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the available commands:\n");
        sb.append("bye: ends our interaction :-(\n");
        sb.append("deadline <description> /by <due date/time>: Creates a Deadline task\n");
        sb.append("delete <idx>: Deletes the task at your chosen index\n");
        sb.append("event <description> /from <start date/time> /to <end date/time>: Creates an Event task\n");
        sb.append("find <queryString>: Finds task with descriptions matching your query regex\n");
        sb.append("list: Displays your current tasks\n");
        sb.append("mark <idx>: Marks the task at your chosen index\n");
        sb.append("save : Saves all tasks in your current list to the database that will be automatically "
                + "loaded during your next session\n");
        sb.append("todo <description>: Creates a ToDo task\n");
        sb.append("unmark <idx>: Unmarks the task at your chosen index");
        return sb.toString();
    }
}
