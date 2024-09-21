package elysia.commands;

import elysia.exceptions.ArgumentFormatException;
import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.Event;
import elysia.tasks.TaskList;

/**
 * Represents a command to create a new event task in the Elysia application.
 * Extends the {@code Command} class and handles the parsing and validation of event task arguments.
 */
public class EventCommand extends Command {
    private String[] args;

    /**
     * Constructs an {@code EventCommand} with the specified task list, file reader/writer, and command arguments.
     *
     * @param taskList the task list to which the event will be added.
     * @param fileReaderWriter the file reader/writer for saving or loading task data.
     * @param args the command arguments, where the first argument is the command type and the second contains the event details.
     */
    public EventCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    /**
     * Executes the {@code EventCommand} to create a new event task.
     * Validates the command arguments, creates the event task, and adds it to the task list.
     *
     * @return a string indicating the result of adding the new event task.
     * @throws EmptyTaskArgumentsException if no arguments are provided for the event command.
     * @throws ArgumentFormatException if the arguments are incorrectly formatted.
     */
    @Override
    public String execute() throws EmptyTaskArgumentsException, ArgumentFormatException {
        StringBuilder output = new StringBuilder();

        if (args.length == 1) {
            throw new EmptyTaskArgumentsException(args[0]);
        }

        String[] eventArgs = args[1].split(" /from | /to ");
        if (eventArgs.length != 3) {
            throw new ArgumentFormatException(args[0]);
        }

        Event event = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
        taskList.addTask(event);
        output.append("Added the task below to your list~\n")
                .append(event.toString()).append("\n")
                .append("Wow! You now have ").append(taskList.getSizeAsString()).append(" tasks in your list!");

        return output.toString();
    }
}
