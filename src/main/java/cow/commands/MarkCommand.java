package cow.commands;

import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.exceptions.CowExceptions;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_EXAMPLE = "mark 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an item in the todo as done.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private int index;
    public MarkCommand(int index) {
        this.index = index - 1;
    }


    /**
     * Marks the task at the specified index in the todo list as completed
     * @param todoList the list of the tasks
     * @param fileSaver filesaver object used to write data to txt
     * @throws CowExceptions any exceptions that might arise from the implementation
     */
    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        todoList.markTask(this.index);
        fileSaver.saveData(todoList);
    }
}
