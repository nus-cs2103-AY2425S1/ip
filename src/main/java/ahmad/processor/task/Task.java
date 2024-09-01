package ahmad.processor.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import ahmad.exceptions.deadline.DeadlineEmptyNameException;
import ahmad.exceptions.deadline.DeadlineInvalidArgsException;
import ahmad.exceptions.deadline.DeadlineInvalidTimeException;
import ahmad.exceptions.event.EventEmptyNameException;
import ahmad.exceptions.event.EventInvalidArgsException;
import ahmad.exceptions.event.EventInvalidTimeException;
import ahmad.exceptions.todo.TodoEmptyNameException;

public abstract class Task {
    private final String name;
    private final boolean state;

    protected Task(String name) {
        this.name = name;
        this.state = false;
    }

    protected Task(Task oldTask, boolean state) {
        this.name = oldTask.name;
        this.state = state;
    }

    public static Task of(TaskType type, String arg) throws DeadlineInvalidArgsException, DeadlineEmptyNameException,
            EventEmptyNameException, EventInvalidArgsException, TodoEmptyNameException,
            DeadlineInvalidTimeException, EventInvalidTimeException {
        switch (type) {
        case Todo:
            if (arg.replaceAll("\\s+", "").isEmpty()) {
                throw new TodoEmptyNameException();
            }
            return new Todo(arg);
        case Deadline:
            final List<String> deadlineArgs = Arrays.asList(arg.split("/by "));
            if (deadlineArgs.get(0).replaceAll("\\s+", "").isEmpty()) {
                throw new DeadlineEmptyNameException();
            }
            if (deadlineArgs.size() != 2 || deadlineArgs.get(1).replaceAll("\\s+", "").isEmpty()) {
                throw new DeadlineInvalidArgsException();
            }

            final LocalDateTime deadline;

            try {
                deadline = LocalDateTime.parse(deadlineArgs.get(1));
            } catch (DateTimeParseException e) {
                throw new DeadlineInvalidTimeException(deadlineArgs.get(1));
            }

            return new Deadline(deadlineArgs.get(0), deadline);
        case Event:
            final List<String> eventArgs = Arrays.asList(arg.split("/"));
            if (eventArgs.get(0).replaceAll("\\s+", "").isEmpty()) {
                throw new EventEmptyNameException();
            }
            if (eventArgs.size() != 3) {
                throw new EventInvalidArgsException();
            }

            final String from;
            final String to;
            if (eventArgs.get(1).startsWith("to")) {
                if (!eventArgs.get(2).startsWith("from")
                        || eventArgs.get(1).replaceAll("\\s+", "").length() == 2
                        || eventArgs.get(2).replaceAll("\\s+", "").length() == 4) {
                    throw new EventInvalidArgsException();
                }
                to = eventArgs.get(1).substring(3);
                from = eventArgs.get(2).substring(5);
            } else if (eventArgs.get(1).startsWith("from")) {
                if (!eventArgs.get(2).startsWith("to")
                        || eventArgs.get(2).replaceAll("\\s+", "").length() == 2
                        || eventArgs.get(1).replaceAll("\\s+", "").length() == 4) {
                    throw new EventInvalidArgsException();
                }
                from = eventArgs.get(1).substring(5);
                to = eventArgs.get(2).substring(3);
            } else {
                throw new EventInvalidArgsException();
            }

            final LocalDateTime fromDate;
            final LocalDateTime toDate;

            try {
                fromDate = LocalDateTime.parse(from.replaceAll("\\s+", ""));
            } catch (DateTimeParseException e) {
                throw new EventInvalidTimeException(from);
            }

            try {
                toDate = LocalDateTime.parse(to.replaceAll("\\s+", ""));
            } catch (DateTimeParseException e) {
                throw new EventInvalidTimeException(to);
            }

            return new Event(eventArgs.get(0), fromDate, toDate);
        default:
            throw new IllegalStateException();
        }

    }

    public abstract Task mark();

    public abstract Task unmark();

    public abstract String getSymbol();

    public abstract String getExtraInformation();

    protected String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm:ss"));
    }

    @Override
    public String toString() {
        return this.getSymbol() + "[" + (state ? "X" : " ") + "] " + name + this.getExtraInformation();
    }
}
