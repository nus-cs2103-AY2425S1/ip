package duck.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * Represents a command to list tasks and events on a specific date.
 * When executed, it filters tasks by their due date or event time and displays them to the user.
 */
public class OnCommand extends Command {

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
        super.execute(tasks, storage, ui);

        String[] words = message.split(" ");
        if (words.length != 2) {
            throw new DuckException("The format for 'On' instruction is:\n"
                    + "on {yyyy-MM-dd}");
        }

        LocalDate date = LocalDate.parse(words[1]);
        AtomicInteger idx = new AtomicInteger(1);

        // Filter and display deadlines and events that are due or occur on the specified date
        System.out.println("Here are the tasks due by "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        Stream<Deadline> deadlineStream = tasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .filter(deadline -> deadline.isOn(date));
        deadlineStream.forEach(deadline -> System.out.println(idx.getAndIncrement() + "." + deadline.toString()));

        idx.set(1);
        System.out.println();


        // Filter and display events that are still happening on the specified date
        System.out.println("Here are the events still happening on "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        Stream<Event> eventStream = tasks.stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event) task)
                .filter(event -> event.isEndingAfter(date));
        eventStream.forEach(event -> System.out.println(idx.getAndIncrement() + "." + event.toString()));
        System.out.println();
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
