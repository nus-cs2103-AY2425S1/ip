package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.message.Message;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Print the message goodbye before termination
     * @param todoList the list of the tasks
     * @param fileSaver filesaver object used to write data to txt
     * @throws CowExceptions any exceptions that might arise from the implementation
     */
    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) {
        Message.printGoodBye();
    }

    /**
     * Returns true to tell the program to terminate
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
