package cow.commands;

import java.time.LocalDate;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/**
 * Creates a due command object.
 **/
public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    public static final String COMMAND_EXAMPLE = "due d/M/yyyy";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show all due items on specified date\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final LocalDate date;

    /**
     * Constructs a DueCommand with the specified due date.
     *
     * @param date the date for which to show due items.
     */
    public DueCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Prints the deadlines that are due at the specified date.
     *
     * @param todoList  the list of tasks.
     * @param ui        the UI object used to interact with the user.
     * @param fileSaver the FileSaver object used to write data to a file.
     * @throws CowExceptions if any exceptions arise from the implementation.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) throws CowExceptions {
        ui.printDue(this.date, todoList);
    }
}
