package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master
/** Creates a Help command object. **/
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Prints all the command and its usage example.
     *
     * @param todoList the list of the tasks.
     * @param fileSaver filesaver object used to write data to txt.
     * @throws CowExceptions any exceptions that might arise from the implementation.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) {
        ui.print(
                ListCommand.MESSAGE_USAGE
                        + "\n" + ByeCommand.MESSAGE_USAGE
                        + "\n" + TodoCommand.MESSAGE_USAGE
                        + "\n" + DeadlineCommand.MESSAGE_USAGE
                        + "\n" + EventCommand.MESSAGE_USAGE
                        + "\n" + MarkCommand.MESSAGE_USAGE
                        + "\n" + UnmarkCommand.MESSAGE_USAGE
                        + "\n" + DeleteCommand.MESSAGE_USAGE
                        + "\n" + DueCommand.MESSAGE_USAGE
                        + "\n" + HelpCommand.MESSAGE_USAGE
                        + "\n" + RecurringCommand.MESSAGE_USAGE
        );
    }
}
