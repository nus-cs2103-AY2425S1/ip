package alexer.command;

import alexer.ui.Response;

/**
 * Displays the help information on the capabilities of Alexer
 * and the commands that are available
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public Response run(String... arguments) {
        return new Response("""
                Alexer, a chat bot to improve your life by managing your tasks.

                Here are commands for Alexer:

                \ttodo <description> - Creates a new To-Do task with a description
                \tevent <description> /from <when> /to <when> - Adds an event with starting and ending time
                \tdeadline <description> /by <date-time> - Add a new deadline task with deadline given
                \tmark <index> - Marks a task as completed/done
                \tlist - Shows all your tasks and numbered by their index
                \tfind <keyword> - Searches for a task with description that contains the given keyword
                \tdelete <index> - Removes the task at the given index from your task list

                Example usage (do try them out!):

                \ttodo do maths homework
                \tlist
                \tfind homework
                \tmark 1
                \tdelete 1
        """);
    }
}
