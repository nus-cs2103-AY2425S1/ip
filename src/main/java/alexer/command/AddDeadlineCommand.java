package alexer.command;

import alexer.Alexer;
import alexer.task.Deadline;
import alexer.task.TaskManager;
import alexer.ui.Response;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A command to create a new deadline task,
 * that consists of a deadline date & time value,
 * supplied by the "/by" argument.
 *
 * @author sayomaki
 */
public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand() {
        super("deadline");
    }

    @Override
    public Response run(String... arguments) {
        int keywordIndex = 0;
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i].equals("/by")) keywordIndex = i;
        }

        String description = Arrays.stream(arguments).limit(keywordIndex)
                .collect(Collectors.joining(" "));
        String by = Arrays.stream(arguments).skip(keywordIndex + 1).collect(Collectors.joining(" "));

        LocalDateTime dateTime;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            dateTime = LocalDateTime.parse(by, dateTimeFormat);
        } catch (DateTimeException e) {
            return new Response("Uh-oh, I failed to understand what is the task deadline!");
        }

        TaskManager taskManager = Alexer.getInstance().getTaskManager();
        Deadline deadline = new Deadline(description, dateTime);
        taskManager.addTask(deadline);
        taskManager.saveTasks();

        return new Response(String.format(
                "No problems! I’ve added the task to your list:\n\n\t%s\n\nYou have %d tasks now.",
                deadline, taskManager.getTaskCount()));
    }
}
