package commands;

import FileSaver.FileSaver;
import TodoList.TodoList;
import exceptions.CowExceptions;
import message.Message;
import tasks.Task;

import java.time.LocalDate;

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
