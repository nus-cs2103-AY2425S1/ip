package cow.commands;

import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.message.Message;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class IncorrectCommand extends Command {
    private final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) {
        Message.print(feedbackToUser);
    }
}
