package tohru.command;

import java.time.LocalDateTime;

import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.DeadlineItem;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * Represents the command to add a deadline task to the to-do list.
 */
public class AddDeadlineCommand extends Command {

    /** Prefix used to invoke the add deadline command. */
    public static final String COMMAND_PREFIX = "deadline";

    /**
     * Initialises the command object.
     *
     * @param arguments Arguments passed to the command.
     */
    public AddDeadlineCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TodoList list, Ui ui, FileStore store) throws TohruException {
        // Check if no arguments are provided
        if (super.arguments == null) {
            throw new TohruException("Missing argument: Please specify description and deadline");
        }

        DeadlineItem newDeadline = parseArguments(super.arguments.trim());
        list.addItem(newDeadline);

        ui.showText("Added deadline entry:",
                newDeadline.toString(),
                String.format("There are now %d total entries", list.getTotal()));

        store.saveTodoList(list.getTodoList());
    }

    private DeadlineItem parseArguments(String arguments) throws TohruException {
        String[] dissectedArgument = arguments.split("/by", 2);
        // Check if all arguments are present
        if (dissectedArgument.length < 2) {
            throw new TohruException("Missing argument: Missing either description or deadline");
        }

        String deadlineContent = dissectedArgument[0];
        // Check for valid description
        if (deadlineContent.isBlank()) {
            throw new TohruException("Missing argument: Please specify description");
        }

        String deadlineStr = dissectedArgument[1];
        // Check for valid deadline
        if (deadlineStr.isBlank()) {
            throw new TohruException("Missing argument: Please specify deadline");
        }

        LocalDateTime deadline = Parser.parseDateTimeString(deadlineStr.trim(), Command.DATE_TIME_FORMATTER);

        return new DeadlineItem(deadlineContent, deadline);
    }
}
