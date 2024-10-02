package moimoi.util.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.exception.InvalidCommandException;
import moimoi.util.exception.InvalidDateTimeException;
import moimoi.util.exception.InvalidPeriodException;
import moimoi.util.exception.MissingArgumentException;
import moimoi.util.exception.MoiMoiException;
import moimoi.util.task.Deadline;
import moimoi.util.task.Event;
import moimoi.util.task.Period;
import moimoi.util.task.Task;
import moimoi.util.task.Todo;

/**
 * Represents a command to add a specific task.
 */
public class AddCommand extends Command {

    private CommandEnum command;
    private String arguments;

    /**
     * Constructs a command to add a task of a specific type, with the specified information.
     *
     * @param command Task type.
     * @param arguments Task information.
     */
    public AddCommand(CommandEnum command, String arguments) {
        super(false);
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Executes task addition.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @return Completion message.
     * @throws MoiMoiException If any part of the task information is missing or invalid,
     *                         or an error related to storage I/O occurs during task list saving.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws MoiMoiException {
        Task task;

        switch (this.command) {
        case TODO:
            task = this.createTodo();
            break;

        case DEADLINE:
            task = this.createDeadline();
            break;

        case EVENT:
            task = this.createEvent();
            break;

        case PERIOD:
            task = this.createPeriod();
            break;

        default:
            throw new InvalidCommandException();
        }

        tasks.add(task);
        storage.save(tasks);
        return "Aight! New task added: " + task.stringUI()
                + "\nWe have " + tasks.getSize() + " tasks in the bag~";
    }

    /**
     * Creates and returns a todo task, corresponding to the task information.
     *
     * @return Todo task corresponding to the task information.
     */
    private Todo createTodo() {
        return new Todo(this.arguments);
    }

    /**
     * Creates and returns a deadline task, corresponding to the task information.
     *
     * @return Deadline task corresponding to the task information.
     * @throws MoiMoiException If any part of the task information is missing or invalid.
     */
    private Deadline createDeadline() throws MoiMoiException {
        try {
            String[] descDeadline = this.arguments.split(" /by ", 2);
            String description = descDeadline[0];
            String deadlineString = descDeadline[1];

            if (description.isEmpty() || deadlineString.isEmpty()) {
                throw new MissingArgumentException();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);

            return new Deadline(description, deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date-time");
        }
    }

    /**
     * Creates and returns an event task, corresponding to the task information.
     *
     * @return Event task corresponding to the task information.
     * @throws MoiMoiException If any part of the task information is missing or invalid.
     */
    private Event createEvent() throws MoiMoiException {
        try {
            String[] descStartEnd = this.arguments.split(" /from ", 2);
            String description = descStartEnd[0];
            String[] startEnd = descStartEnd[1].split(" /to ", 2);
            String startString = startEnd[0];
            String endString = startEnd[1];

            if (description.isEmpty() || startString.isEmpty() || endString.isEmpty()) {
                throw new MissingArgumentException();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime start = LocalDateTime.parse(startString, formatter);
            LocalDateTime end = LocalDateTime.parse(endString, formatter);

            return new Event(description, start, end);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date-time");
        }
    }

    /**
     * Creates and returns a period task, corresponding to the task information.
     *
     * @return Period task corresponding to the task information.
     * @throws MoiMoiException If any part of the task information is missing or invalid.
     */
    private Period createPeriod() throws MoiMoiException {
        try {
            String[] descPeriod = this.arguments.split(" /for ", 2);
            String description = descPeriod[0];
            String periodString = descPeriod[1];

            if (description.isEmpty() || periodString.isEmpty()) {
                throw new MissingArgumentException();
            }

            double period = Double.parseDouble(periodString);

            return new Period(description, period);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        } catch (NumberFormatException e) {
            throw new InvalidPeriodException();
        }
    }

}
