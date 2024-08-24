package cow.commands;

import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.exceptions.CowExceptions;
import cow.message.Message;
import cow.tasks.Deadlines;
import cow.tasks.Task;

import java.time.LocalDateTime;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_EXAMPLE = "deadline return book /by 2/12/2019 1800";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Deadline item to the list.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final LocalDateTime by;
    private final String description;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description.trim();
        this.by = by;
    }

    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        Task t = new Deadlines(this.description, this.by);
        todoList.add(t);
        fileSaver.saveData(todoList);
        Message.printAddedTask(t, todoList);
    }
}
