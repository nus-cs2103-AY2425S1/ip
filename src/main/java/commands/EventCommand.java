package commands;

import applemazer.Storage;
import applemazer.TaskList;
import tasks.Task;
import tasks.Event;
import java.time.DateTimeException;

/**
 * Class that represents the "event" command.
 */
public class EventCommand extends Command {
    private final String desc, from, to;

    /**
     * Constructor for an {@code EventCommand} object.
     * <p>
     * Note that setting the time is optional.
     * @param desc Description of the event.
     * @param from The start date (and time) of the event, as a String.
     * @param to The end date (and time) of the event, as a String.
     */
    public EventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the "event" command which adds an {@code Event} task to the task list.
     * @param tasks   The task list to use.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task task = new Event(desc, from, to);
            tasks.add(task);
            task.printTaskAddedMessage();
            storage.save();
        } catch (DateTimeException e) {
            System.out.println("""
                               OOPS!!! The description of event is wrong.
                               Try 'event <description> /from <date1> /to <date2>'.
                               <date> should be <yyyy-mm-dd> <HHmm> or <dd/MM/yyyy> <HHmm>.
                               It is not necessary to input the time!
                               """);
        }
    }

    /**
     * Returns {@code true} as the chatbot should continue running after executing the "event" command.
     * @return {@code true}
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
