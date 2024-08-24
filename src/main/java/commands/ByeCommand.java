package commands;

import FileSaver.FileSaver;
import TodoList.TodoList;
import message.Message;

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
