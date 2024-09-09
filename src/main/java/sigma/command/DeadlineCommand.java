package sigma.command;

import java.time.LocalDateTime;

import sigma.exception.SigmaException;
import sigma.task.DeadlineTask;
import sigma.utils.Parser;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;



/**
 * Represents the command to add a deadline task.
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String[] commandArray) {
        super(commandArray);
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws SigmaException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        String missingTaskMessage = "What the sigma? You're missing the task! "
                + "Write \"deadline <task> /by <deadline>\"!";
        String missingDeadlineMessage = "What the sigma? You're missing the deadline! "
                + "Write \"deadline <task> /by <deadline>\"!";
        if (commandArray.length < 2) {
            throw new SigmaException(missingTaskMessage);
        }
        String[] deadlineSplit = commandArray[1].split(" /by ");
        assert deadlineSplit.length > 0 : "Deadline split length cannot be 0";
        if (deadlineSplit.length < 2) {
            throw new SigmaException(missingDeadlineMessage);
        }
        LocalDateTime dateTime = Parser.parseLocalDateTime(deadlineSplit[1]);
        DeadlineTask deadlineTask = new DeadlineTask(deadlineSplit[0], dateTime);
        tasks.add(deadlineTask);
        return "Wow! Keeping yourself busy! Added: \n" + deadlineTask
                + "\nNow you have " + tasks.size() + " tasks in the list!";
    }

}
