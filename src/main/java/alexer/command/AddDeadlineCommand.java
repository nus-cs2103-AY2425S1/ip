package alexer.command;

import alexer.Alexer;
import alexer.task.Task;
import alexer.task.TaskManager;
import alexer.ui.Response;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import static alexer.Prompter.MESSAGE_ADD_DEADLINE_TASK;

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
            return new Response("Uh-oh, I failed to understand what is the task deadline!",
                    Response.ResponseType.ERROR);
        }

        TaskManager taskManager = Alexer.getInstance().getTaskManager();
        Task deadline = taskManager.createDeadline(description, dateTime);

        return new Response(String.format("%s\n\n\t%s\n\nYou have %d tasks now.",
                MESSAGE_ADD_DEADLINE_TASK,
                deadline, taskManager.getTaskCount()));
    }
}
