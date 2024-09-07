package duck.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import duck.common.Message;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.data.task.Task;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * Represents a command to list tasks and events on a specific date.
 * When executed, it filters tasks by their due date or event time and displays them to the user.
 */
public class OnCommand extends Command {

    private static final String ERROR_MESSAGE_ON_COMMAND = "The format for 'On' instruction is:\n"
            + "on {yyyy-MM-dd}";
    private static final String PATTERN_ON_COMMAND = "MMM dd yyyy";
    private static final String MESSAGE_EVENTS_HAPPENING_ON = "Here are the events still happening on ";
    private static final String MESSAGE_DEADLINES_DUE_BY = "Here are the tasks due by ";

    /**
     * Constructs an OnCommand with the specified message.
     *
     * @param message The message associated with the command, which should contain the date to filter tasks and events.
     */
    public OnCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by filtering deadlines and events that are due or occur on the specified date.
     * It validates the input format and displays the filtered tasks and events to the user.
     *
     * @param tasks The list of tasks from which tasks and events will be filtered.
     * @param storage The storage system (not used in this command).
     * @param ui The user interface for displaying the filtered tasks and events.
     * @throws DuckException If the input format is incorrect.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        LocalDate date = getLocalDate();

        // Filter deadlines and events that are due or occur on the specified date
        Stream<Deadline> deadlineStream = filterDeadlineTasks(tasks, date);
        Stream<Event> eventStream = filterEventTasks(tasks, date);

        // Print the filtered tasks and events
        printFilteredDeadlines(date, deadlineStream);
        System.out.println(); // Print a newline between the filtered deadlines and events
        printFilteredEvents(date, eventStream);
    }

    private LocalDate getLocalDate() throws DuckException {
        String[] words = message.split(" ");
        if (words.length != 2) {
            throw new DuckException(ERROR_MESSAGE_ON_COMMAND);
        }

        try {
            return LocalDate.parse(words[1]);
        } catch (DateTimeParseException e) {
            throw new DuckException(ERROR_MESSAGE_ON_COMMAND);
        }
    }

    private void printFilteredEvents(LocalDate date, Stream<Event> eventStream) {
        System.out.println(MESSAGE_EVENTS_HAPPENING_ON
                + date.format(DateTimeFormatter.ofPattern(PATTERN_ON_COMMAND)) + ":");
        printTasks(eventStream);
    }

    private void printFilteredDeadlines(LocalDate date, Stream<Deadline> deadlineStream) {
        System.out.println(MESSAGE_DEADLINES_DUE_BY
                + date.format(DateTimeFormatter.ofPattern(PATTERN_ON_COMMAND)) + ":");
        printTasks(deadlineStream);
    }

    private Stream<Deadline> filterDeadlineTasks(TaskList tasks, LocalDate date) {
        return tasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .filter(deadline -> deadline.isOn(date));
    }

    private Stream<Event> filterEventTasks(TaskList tasks, LocalDate date) {
        return tasks.stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event) task)
                .filter(event ->
                        event.isStartingBefore(date)
                                && event.isEndingAfter(date));

    }

    private void printTasks(Stream<? extends Task> taskStream) {
        AtomicInteger idx = new AtomicInteger(Message.TASK_LIST_FIRST_INDEX);
        taskStream.forEach(task -> System.out.println(idx.getAndIncrement() + "." + task.toString()));
    }

    /**
     * Determines whether the command signifies an exit operation.
     *
     * @return false, as the OnCommand does not signify an exit operation.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
