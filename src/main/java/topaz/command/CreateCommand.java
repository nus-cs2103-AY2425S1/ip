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

public class CreateCommand extends Command {
    // Todo, Deadline, Event

    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String detail;
    public CreateCommand(String keyword, String detail) {
        super(keyword);
        this.detail = detail;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
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
                return;
            }
            ui.showAddTask(task, tasks.getSize());
            storage.save(tasks);
        } catch (InvalidTaskException e) {
            ui.showException(e);
        } catch (IOException e) {
            ui.showSaveIOEException(e);
        }
    }

    private Task addTodo(TaskList taskList) throws InvalidTaskException {
        try {
            String description = this.detail.substring(5);
            Todo todo = new Todo(description);
            taskList.addTask(todo);
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

        if (deadlineMatcher.find() && byMatcher.find()) {
            String description = deadlineMatcher.group(1).trim();
            String by = byMatcher.group(1).trim();
            try {
                Deadline deadline = new Deadline(description, LocalDateTime.parse(by, dateTimeFormatter));
                taskList.addTask(deadline);
                return deadline;
            } catch (DateTimeParseException dateTimeParseException) {
                throw new InvalidTaskException(Topaz.TaskType.D);
            }
        } else {
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
                LocalDateTime start = LocalDateTime.parse(from, dateTimeFormatter);
                LocalDateTime end = LocalDateTime.parse(to, dateTimeFormatter);
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
