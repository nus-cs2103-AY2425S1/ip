package commands;

import java.time.DateTimeException;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.DuplicateHandler;
import tasks.Event;
import tasks.Task;

/**
 * Class that represents the "event" command.
 */
public class EventCommand extends Command {
    private final String desc;
    private final String from;
    private final String to;

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
     *
     * @param tasks            The task list to use.
     * @param storage          The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui               The Ui object used to generate the string to print.
     * @param duplicateHandler The duplicate handler to use if necessary.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, DuplicateHandler duplicateHandler) {
        try {
            Task task = new Event(desc, from, to);
            tasks.add(task);
            duplicateHandler.addNewEvent(((Event) task).getKey());
            storage.saveTaskList();
            return ui.getTaskAddedMessage(task, tasks.size());
        } catch (DateTimeException e) {
            return """
                   OOPS!!! The description of event is wrong.
                   Try 'event <description> /from <date1> /to <date2>'.
                   <date> should be <yyyy-mm-dd> <HHmm> or <dd/MM/yyyy> <HHmm>.
                   It is not necessary to input the time!
                   """;
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
