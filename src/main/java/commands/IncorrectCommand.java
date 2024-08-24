package commands;

import FileSaver.FileSaver;
import TodoList.TodoList;
import message.Message;

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
