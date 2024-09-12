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

/**
 * Base abstract class for tasks.
 */
public abstract class Task {
    private final String name;
    private final boolean state;

    /**
     * Constructs abstract task instance based on name.
     * State defaults to false.
     *
     * @param name Name of task.
     */
    protected Task(String name) {
        this.name = name;
        this.state = false;
    }

    /**
     * Constructs abstract task instance based on an old copy.
     *
     * @param oldTask Old task to be copied from.
     * @param state   New state of task.
     */
    protected Task(Task oldTask, boolean state) {
        this.name = oldTask.name;
        this.state = state;
    }

    /**
     * Factory method for tasks.
     *
     * @param type Type of task being created.
     * @param arg  Argument for task.
     * @return A new instance of the task being created.
     * @throws TodoEmptyNameException       If name is empty.
     * @throws DeadlineInvalidArgsException If prompt/argument is invalid.
     * @throws DeadlineEmptyNameException   If name is empty.
     * @throws EventEmptyNameException      If name is empty.
     * @throws EventInvalidArgsException    If prompt/argument is invalid.
     * @throws DeadlineInvalidTimeException If given time is invalid.
     * @throws EventInvalidTimeException    If given time is invalid.
     */
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

    /**
     * Compares two different task based on time ascendingly
     *
     * @param a First task to be compared
     * @param b Second task to be compared
     * @return Based on comparator function
     */
    public static int compareTimeAscending(Task a, Task b) {
        long aTime = a.getTime();
        long bTime = b.getTime();

        return aTime - bTime < 0 ? -1 : 1;
    }

    protected abstract long getTime();

    /**
     * Marks current task as done.
     *
     * @return new, immutable task.
     */
    public abstract Task mark();

    /**
     * Marks current task as not done.
     *
     * @return new, immutable task.
     */
    public abstract Task unmark();

    /**
     * Returns symbol of current task.
     *
     * @return symbol of current task.
     */
    public abstract String getSymbol();

    /**
     * Returns extra information about the task.
     *
     * @return task information.
     */
    public abstract String getExtraInformation();

    protected String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm:ss"));
    }

    @Override
    public String toString() {
        return this.getSymbol() + "[" + (state ? "X" : " ") + "] " + name + this.getExtraInformation();
    }
}
