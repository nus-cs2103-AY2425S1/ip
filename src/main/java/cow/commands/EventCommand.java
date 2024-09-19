package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.tasks.Event;
import cow.tasks.Task;
import cow.todolist.TodoList;


// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/**
 * Creates an Event command object.
 **/
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_EXAMPLE = "event project meeting /from Mon 2pm /to 4pm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Event item to the list.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final String from;
    private final String to;
    private final String description;

    /**
     * Creates an EventCommand instance.
     *
     * @param description Description of the event.
     * @param from        Start time of the event.
     * @param to          End time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description.trim();
        this.from = from.trim();
        this.to = to.trim();
    }

    /**
     * Creates an event task and adds to the todo list.
     *
     * @param todoList  the list of tasks.
     * @param ui        the UI object used to interact with the user.
     * @param fileSaver the FileSaver object used to write data to a file.
     * @throws CowExceptions if any exceptions arise from the implementation.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) throws CowExceptions {
        Task t = new Event(this.description, this.from, this.to);
        todoList.add(t);
        fileSaver.saveData(todoList);
        ui.printAddedTask(t, todoList);
    }
}
