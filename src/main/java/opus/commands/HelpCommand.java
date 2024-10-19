package opus.commands;

import opus.Storage;
import opus.TaskList;

/**
 * Represents a command to show the help message.
 */
public class HelpCommand implements Command {
    /**
     * Executes the command.
     *
     * @param taskList The task list.
     * @param storage The storage.
     * @return The response to the user.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Here are the commands you can use:\n"
             + "1. list - List all tasks\n"
             + "2. mark <index> - Mark a task as done\n"
             + "3. delete <index> - Delete a task\n"
             + "4. deadline <task> /by <end-date> - Add a deadline\n"
             + "5. event <task> /from <start-date> /to <end-date> - Add an event\n"
             + "6. todo <task> - Add a Todo task\n"
             + "7. bye - Exit the application\n"
             + "8. help - Show this help message";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
