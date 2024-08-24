package cow.commands;

import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.message.Message;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;
    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) {
        Message.printGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
