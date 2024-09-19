package cow.commands;

import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;
import javafx.application.Platform;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/**
 * Creates a bye command object.
 **/
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Prints the message goodbye before termination.
     *
     * @param todoList  the list of the tasks.
     * @param ui        the UI object used to interact with the user.
     * @param fileSaver filesaver object used to write data to txt.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) {
        ui.printGoodBye();
        Platform.exit();
    }

    /**
     * Returns true to tell the program to terminate.
     *
     * @return boolean.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
