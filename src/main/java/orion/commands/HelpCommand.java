package orion.commands;

import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * Represents a command to show a help screen.
 */
public class HelpCommand extends Command {

    /**
     * Constructs an {@code HelpCommand}.
     */
    public HelpCommand() {
        super(false);
    }

    /**
     * Executes the help command by printing a list of the supported user commands.
     *
     * @param tasks the {@link TaskList} containing the existing user tasks (not used in this method)
     * @param storage the {@link Storage} object used for data storage (not used in this method)
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are a list of commands that you can use!\n\n");
        stringBuilder.append("list: Shows a list of all your tasks, together with their "
                 + "completion status and deadlines if applicable.\n\n");
        stringBuilder.append("help: Brings up the help page (which is what you are reading right now!)\n\n");
        stringBuilder.append("mark <task number>: Marks the specified task as completed.\n\n");
        stringBuilder.append("unmark <task number>: Marks the specified task as uncompleted.\n\n");
        stringBuilder.append("delete <task number>: Deletes the specified task.\n\n");
        stringBuilder.append("todo <task description>: Adds the specified task as a Todo task.\n\n");
        stringBuilder.append("deadline <task description> /by <yyyy-mm-dd>: "
                + "Adds the specified task as a Deadline task with the specified deadline.\n\n");
        stringBuilder.append("event <task description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>: "
                + "Adds the specified task as an Event task with the specified start and end dates.\n\n");
        stringBuilder.append("find <query>: Shows a list of all tasks which fit the specified query\n\n");
        stringBuilder.append("bye: Saves the current task list and exits the chatbot.");
        return stringBuilder.toString();
    }
}
