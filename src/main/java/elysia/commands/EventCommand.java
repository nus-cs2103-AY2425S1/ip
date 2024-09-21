package elysia.commands;

import elysia.exceptions.ArgumentFormatException;
import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.Event;
import elysia.tasks.TaskList;
import elysia.ui.Message;

import java.util.Objects;

public class EventCommand extends Command {
    String[] args;

    public EventCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

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
        assert(!Objects.equals(taskList.getSizeAsString(), "0"));
        output.append("Added the task below to your list~\n").append(event.toString()).append("\n");
        output.append("Wow! You now have ").append(taskList.getSizeAsString()).append(" tasks in your list!");
        return output.toString();
    }
}
