package cow.commands;

import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.exceptions.CowExceptions;
import cow.message.Message;

import java.time.LocalDate;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    public static final String COMMAND_EXAMPLE = "due d/M/yyyy";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show all due items on specified date\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final LocalDate date;
    public DueCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        Message.printDue(this.date, todoList);
    }
}
