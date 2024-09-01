package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master
/** Creates a bye command object. **/
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Prints the message goodbye before termination.
     * @param todoList the list of the tasks.
     * @param fileSaver filesaver object used to write data to txt.
     * @throws CowExceptions any exceptions that might arise from the implementation.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) {
        ui.printGoodBye();
    }

    /**
     * Returns true to tell the program to terminate.
     * @return boolean.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
