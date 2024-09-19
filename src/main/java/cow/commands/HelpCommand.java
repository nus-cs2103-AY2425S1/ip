package cow.commands;

import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/**
 * Creates a Help command object.
 **/
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Prints all the commands and their usage examples.
     *
     * @param todoList  the list of tasks.
     * @param ui        the UI object used to interact with the user.
     * @param fileSaver the FileSaver object used to write data to a file.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) {
        ui.print(
                ListCommand.MESSAGE_USAGE
                        + "\n" + ByeCommand.MESSAGE_USAGE + "\n"
                        + "\n" + TodoCommand.MESSAGE_USAGE + "\n"
                        + "\n" + DeadlineCommand.MESSAGE_USAGE + "\n"
                        + "\n" + EventCommand.MESSAGE_USAGE + "\n"
                        + "\n" + MarkCommand.MESSAGE_USAGE + "\n"
                        + "\n" + UnmarkCommand.MESSAGE_USAGE + "\n"
                        + "\n" + DeleteCommand.MESSAGE_USAGE + "\n"
                        + "\n" + DueCommand.MESSAGE_USAGE + "\n"
                        + "\n" + HelpCommand.MESSAGE_USAGE + "\n"
                        + "\n" + RecurringCommand.MESSAGE_USAGE + "\n"
                        + "\n" + FindCommand.MESSAGE_USAGE + "\n"
        );
    }
}
