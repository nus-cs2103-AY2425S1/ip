package cow.commands;

import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.exceptions.CowExceptions;
import cow.message.Message;
import cow.tasks.Event;
import cow.tasks.Task;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_EXAMPLE = "event project meeting /from Mon 2pm /to 4pm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Event item to the list.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final String from;
    private final String to;
    private final String description;

    public EventCommand(String description, String from, String to) {
        this.description = description.trim();
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        Task t = new Event(this.description, this.from, this.to);
        todoList.add(t);
        fileSaver.saveData(todoList);
        Message.printAddedTask(t, todoList);
    }
}
