package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;

public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = "Here are the available commands:\n"
            + "1. todo [task description] - Adds a ToDo task\n"
            + "2. deadline [task description] /by [date] - Adds a Deadline task\n"
            + "3. event [task description] /from [start time] /to [end time] - Adds an Event task\n"
            + "4. list - Lists all tasks\n"
            + "5. mark [task number] - Marks a task as done\n"
            + "6. unmark [task number] - Unmarks a task\n"
            + "7. delete [task number] - Deletes a task\n"
            + "8. find [keyword] - Finds tasks containing the keyword\n"
            + "9. bye - Exits the application\n"
            + "10. help - Displays this help message";


    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        ui.showLine();
        ui.showMessage(HELP_MESSAGE);
        ui.showLine();
        return HELP_MESSAGE;
    }
}
