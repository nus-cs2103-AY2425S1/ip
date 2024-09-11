package topaz.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import topaz.exception.InvalidTaskException;
import topaz.exception.InvalidTimeException;
import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.main.Topaz;
import topaz.task.Deadline;
import topaz.task.Event;
import topaz.task.Task;
import topaz.task.Todo;
import topaz.ui.Ui;

/**
 * Represents a command to create a new task in the task management system.
 * Depending on the keyword, the command can create a Todo, Deadline, or Event task.
 */
public class CreateCommand extends Command {
    // Todo, Deadline, Event

    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String detail;
    /**
     * Constructs a CreateCommand with the specified keyword and detail.
     *
     * @param keyword The keyword specifying the type of task to create (e.g., "todo", "deadline", "event").
     * @param detail  The detail string containing the task description and time specifications.
     */
    public CreateCommand(String keyword, String detail) {
        super(keyword);
        this.detail = detail;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task;
            switch (super.keyword) {
            case "todo":
                task = addTodo(tasks);
                break;
            case "deadline":
                task = addDeadline(tasks);
                break;
            case "event":
                task = addEvent(tasks);
                break;
            default:
                return "";
            }
            storage.save(tasks);
            return ui.showAddTask(task, tasks.getSize());
        } catch (InvalidTaskException e) {
            return ui.showException(e);
        } catch (IOException e) {
            return ui.showSaveIoeException(e);
        }
    }

    private Task addTodo(TaskList taskList) throws InvalidTaskException {
        try {
            String description = this.detail.substring(5);
            Todo todo = new Todo(description);
            int originalSize = taskList.getSize();
            taskList.addTask(todo);
            assert taskList.getSize() == originalSize + 1 : "Error in adding new taskList";
            return todo;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException(Topaz.TaskType.T);
        }
    }

    private Task addDeadline(TaskList taskList) throws InvalidTaskException {
        String deadlinePattern = "deadline (.*?) /by";
        String byPattern = "/by (.*)";

        Pattern ddlPatternCompiled = Pattern.compile(deadlinePattern);
        Pattern byPatternCompiled = Pattern.compile(byPattern);

        Matcher deadlineMatcher = ddlPatternCompiled.matcher(this.detail);
        Matcher byMatcher = byPatternCompiled.matcher(this.detail);

        if (!deadlineMatcher.find() || !byMatcher.find()) {
            throw new InvalidTaskException(Topaz.TaskType.D);
        }

        String description = deadlineMatcher.group(1).trim();
        String by = byMatcher.group(1).trim();
        try {
            Deadline deadline = new Deadline(description, LocalDateTime.parse(by, DATE_TIME_FORMATTER));
            taskList.addTask(deadline);
            assert taskList.find(description).getSize() > 0 : "Fail to add to taskList";
            return deadline;
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidTaskException(Topaz.TaskType.D);
        }
    }

    private Task addEvent(TaskList taskList) throws InvalidTaskException {
        String eventPattern = "event (.*?) /from";
        String fromPattern = "/from (.*?) /to";
        String toPattern = "/to (.*)";

        Pattern eventPatternCompiled = Pattern.compile(eventPattern);
        Pattern fromPatternCompiled = Pattern.compile(fromPattern);
        Pattern toPatternCompiled = Pattern.compile(toPattern);

        Matcher eventMatcher = eventPatternCompiled.matcher(detail);
        Matcher fromMatcher = fromPatternCompiled.matcher(detail);
        Matcher toMatcher = toPatternCompiled.matcher(detail);

        if (eventMatcher.find() && fromMatcher.find() && toMatcher.find()) {
            String description = eventMatcher.group(1).trim();
            String from = fromMatcher.group(1).trim();
            String to = toMatcher.group(1).trim();
            try {
                LocalDateTime start = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
                LocalDateTime end = LocalDateTime.parse(to, DATE_TIME_FORMATTER);
                if (start.isAfter(end)) {
                    throw new InvalidTimeException(Topaz.TaskType.E, start, end);
                }
                Event event = new Event(description, start, end);
                taskList.addTask(event);
                return event;
            } catch (DateTimeParseException dateTimeParseException) {
                throw new InvalidTaskException(Topaz.TaskType.E);
            }
        } else {
            throw new InvalidTaskException(Topaz.TaskType.E);
        }
    }
}
