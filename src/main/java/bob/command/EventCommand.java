package bob.command;

import bob.Bob;
import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingArgumentException;
import bob.task.Event;
import bob.task.Task;

public class EventCommand extends Command {
    public static final String COMMAND = "event";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String argument) {
        int fromIndex = argument.lastIndexOf("/from ");
        int toIndex = argument.lastIndexOf("/to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new MissingArgumentException("'from' and 'to' arguments to add an event");
        }

        Task task = getTask(argument, fromIndex, toIndex);
        tasks.add(task);
        ui.printWithFormat("added: " + task);
    }

    private static Task getTask(String argument, int fromIndex, int toIndex) {
        String desc = fromIndex < toIndex
                ? argument.substring(0, fromIndex)
                : argument.substring(0, toIndex);
        desc = desc.strip();
        if (desc.isBlank()) {
            throw new MissingArgumentException("description of the event");
        }

        String from = fromIndex < toIndex
                ? argument.substring(fromIndex + 6, toIndex)
                : argument.substring(fromIndex + 6);
        from = from.strip();

        String to = fromIndex < toIndex
                ? argument.substring(toIndex + 4)
                : argument.substring(toIndex + 4, fromIndex);
        to = to.strip();

        return new Event(desc, Bob.parseDateTime(from), Bob.parseDateTime(to));
    }
}
