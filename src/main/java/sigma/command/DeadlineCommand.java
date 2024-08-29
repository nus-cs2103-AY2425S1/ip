package sigma.command;
import sigma.Parser;
import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;
import sigma.exception.SigmaException;
import sigma.task.DeadlineTask;

import java.time.LocalDateTime;

/**
 * Represents the command to add a deadline task.
 */
public class DeadlineCommand extends Commands {

    public DeadlineCommand(String[] split) {
        super(split);
    }

    /**
     * Adds a deadline task to the task list.
     * @param tasks
     * @param ui
     * @param storage
     * @throws SigmaException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        Parser parser = new Parser();
        if (split.length < 2) {
            throw new SigmaException("What the sigma? You're missing the task! " +
                    "Write \"deadline <task> /by <deadline>\"!");
        }
        String[] deadlineSplit = split[1].split(" /by ");
        if (deadlineSplit.length < 2) {
            throw new SigmaException("What the sigma? You're missing the deadline! " +
                    "Write \"deadline <task> /by <deadline>\"!");
        }
        LocalDateTime dateTime = parser.dateTimeParse(deadlineSplit[1]);
        DeadlineTask deadlineTask = new DeadlineTask(deadlineSplit[0], dateTime);
        tasks.add(deadlineTask);
        ui.print("Wow! Keeping yourself busy! Added: \n" + deadlineTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list!");
    }
}
