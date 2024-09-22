package commands;

import storage.Storage;
import storage.TaskList;

/**
 * Represents a command that displays a help message with a list of available commands.
 * The HelpCommand class provides users with instructions on how to interact with the application.
 */
public class HelpCommand implements Command {

    /**
     * Executes the help command, displaying a list of available commands and usage instructions.
     * The method prints a help message to the user.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @return String that denotes a response that is displayed to the user
     */
    @Override
    public String execute(Storage storage, TaskList master) {
        return """
                        Friday > Hello! I'm Friday! Your personal chatbot for ensuring you get things done by Friday ;)
                        To create a new task, first choose a task type:

                        1. Todo - No end date
                        2. Deadline - Has end date
                        3. Event - Has start and end date
                        
                        Then do either :
                        Todo - add <name>
                        Deadline - add <name> | <deadline date>
                        Event - add <name> | <start date> | <end date>

                        Other commands:
                        help            displays this page
                        list            lists all tasks available
                        mark <index>    mark a task as completed
                        unmark <index>  mark a task as incomplete
                        remove <index>  remove task from list
                        find <keyword>  find keyword in list
                        bye             exit program
                        """;
    }
}
