package alexer.command;

import alexer.Alexer;
import alexer.task.Event;
import alexer.task.TaskManager;
import alexer.ui.Response;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A command to create a new event task,
 * that consists of an event starting date & time value,
 * as well as an event ending date & time value,
 * supplied by the "/from" and "/to" arguments respectively.
 *
 * @author sayomaki
 */
public class AddEventCommand extends Command {
    public AddEventCommand() {
        super("event");
    }

    @Override
    public Response run(String... arguments) {
        int fromIndex = 0;
        int toIndex = 0;
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i].equals("/from")) fromIndex = i;
            if (arguments[i].equals("/to")) toIndex = i;
        }

        String description = Arrays.stream(arguments).limit(fromIndex)
                .collect(Collectors.joining(" "));
        String from = Arrays.stream(arguments).limit(toIndex).skip(fromIndex + 1)
                .collect(Collectors.joining(" "));
        String to = Arrays.stream(arguments).skip(toIndex + 1).collect(Collectors.joining(" "));

        TaskManager taskManager = Alexer.getInstance().getTaskManager();
        Event event = new Event(description, from, to);
        taskManager.addTask(event);
        taskManager.saveTasks();

        return new Response(String.format(
                "Noted! I’ve added a new event to your tasks:\n\n\t%s\n\nYou have %d tasks now.",
                event, taskManager.getTaskCount()));
    }
}
