package duck.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.storage.Storage;
import duck.ui.Ui;

import java.util.stream.Stream;

public class OnCommand extends Command {

    public OnCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        String[] words = message.split(" ");
        if (words.length != 2) {
            throw new DuckException("The format for 'On' instruction is:\n"
                    + "on {yyyy-MM-dd}");
        }
        LocalDate date = LocalDate.parse(words[1]);
        AtomicInteger idx = new AtomicInteger(1);

        System.out.println("Here are the tasks due by " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        Stream<Deadline> deadlineStream = tasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .filter(deadline -> deadline.isOn(date));
        deadlineStream.forEach(deadline -> System.out.println(idx.getAndIncrement() + "." + deadline.toString()));

        idx.set(1);
        System.out.println();

        System.out.println("Here are the events still happening on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        Stream<Event> eventStream = tasks.stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event) task)
                .filter(event -> event.isEndingAfter(date));
        eventStream.forEach(event -> System.out.println(idx.getAndIncrement() + "." + event.toString()));
        System.out.println();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
