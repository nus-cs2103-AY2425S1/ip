package sigma.command;

import sigma.Parser;
import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;
import sigma.exception.SigmaException;
import sigma.task.EventTask;

import java.time.LocalDateTime;

/**
 * Represents the command to add an event task.
 */
public class EventCommand extends Commands {

    public EventCommand(String[] split) {
        super(split);
    }

    /**
     * Adds an event task to the task list.
     * @param tasks
     * @param ui
     * @param storage
     * @throws SigmaException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        Parser parser = new Parser();
        String SYNTAX = "Write \"event <task> /from <start time> /to <end time>\"!";
        if (split.length < 2) {
            throw new SigmaException("What the sigma? You're missing the task! " + SYNTAX);
        }
        String[] eventSplit = split[1].split(" /from ");
        if (eventSplit.length < 2) {
            throw new SigmaException("What the sigma? You're missing the timing! " + SYNTAX);
        }
        String[] timing = eventSplit[1].split(" /to ");
        if (timing.length < 2) {
            throw new SigmaException("What the sigma? You're missing the end timing! " + SYNTAX);
        }
        LocalDateTime from = parser.dateTimeParse(timing[0]);
        LocalDateTime to = parser.dateTimeParse(timing[1]);
        EventTask eventTask = new EventTask(eventSplit[0], from, to);
        tasks.add(eventTask);
        ui.print("You're a busy bee! Added: \n" + eventTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list!");
    }

}
