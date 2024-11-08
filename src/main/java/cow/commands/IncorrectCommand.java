package cow.commands;

import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/**
 * Creates an Incorrect command object.
 **/
public class IncorrectCommand extends Command {
    private final String feedbackToUser;

    /**
     * Constructs an IncorrectCommand with the specified feedback message.
     *
     * @param feedbackToUser the feedback message to be shown to the user.
     */
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Prints the feedback to the user due to an incorrect command.
     *
     * @param todoList  the list of the tasks.
     * @param ui        the UI object used to interact with the user.
     * @param fileSaver filesaver object used to write data to txt.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) {
        ui.print(feedbackToUser);
    }
}
