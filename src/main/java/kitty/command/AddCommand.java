package kitty.command;

import kitty.Parser;
import kitty.Storage;
import kitty.TaskList;
import kitty.Ui;
import kitty.kittyexceptions.DeadlineException;
import kitty.kittyexceptions.EventException;
import kitty.kittyexceptions.TaskException;
import kitty.tasks.Deadline;
import kitty.tasks.Event;
import kitty.tasks.Task;
import kitty.tasks.Todo;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCommand extends Command {
    private final String commandBody;
    private final Storage storage;
    public static final Pattern TODO_PATTERN =
            Pattern.compile("^todo\\s+(.+)\\s*$");
    public static final Pattern DEADLINE_PATTERN =
            Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(\\d{4}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2})\\s*$");
    public static final Pattern EVENT_PATTERN =
            Pattern.compile("^event\\s+(.+)\\s+/from\\s+(\\d{4}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2})\\s+"
                    + "/to\\s+(\\d{4}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2})\\s*$");

    public AddCommand(Ui ui, TaskList taskList, String commandBody, Storage storage) {
        super(ui, taskList);
        this.commandBody = commandBody;
        this.storage = storage;
    }

    @Override
    public String run() {
        String[] parts = commandBody.split(" ", 2);
        Task task;

        // create task if input is valid
        switch (parts[0]) {
        case "todo" -> {
            return handleTodo();
        }
        case "deadline" -> {
            return handleDeadline();
        }
        case "event" -> {
            return handleEvent();
        }
        default -> {
            return "default addCommand";
        }
        }
    }

    private String addTaskToList(Task task) {
        int size = tasks.addTask(task);
        if (size == -1) {
            return "task not created";
        }

        // if successfully add task to list, update storage and return data back
        String data = task.getTaskData();
        try {
            System.out.println(data);
            storage.addContent(data);
            return ui.showAddTaskMessage(task, size);
        } catch (IOException e) {
            String fileWritingFailMessage = "File writing unsuccessful.\n"
                    + "This task is not updated to hard disk.";
            return ui.showErrorMessage(fileWritingFailMessage);
        }
    }

    private String handleTodo() {
        Matcher matcher = TODO_PATTERN.matcher(commandBody);

        if (!matcher.matches()) {
            return new TaskException().toString();
        }

        String name = matcher.group(1);
        Task tmp = new Todo(name);

        return addTaskToList(tmp);
    }

    private String handleDeadline() {
        Matcher matcher = DEADLINE_PATTERN.matcher(commandBody);

        if (!matcher.matches()) {
            return new DeadlineException().toString();
        }

        String name = matcher.group(1);
        String dateTime = matcher.group(2);

        Task tmp = new Deadline(name, Parser.parseDateTime(dateTime));
        return addTaskToList(tmp);
    }

    private String handleEvent() {
        Matcher matcher = EVENT_PATTERN.matcher(commandBody);

        if (!matcher.matches()) {
            return new EventException().toString();
        }

        String name = matcher.group(1);
        String from = matcher.group(2);
        String to = matcher.group(3);

        Task tmp = new Event(name, Parser.parseDateTime(from), Parser.parseDateTime(to));
        return addTaskToList(tmp);
    }
}
