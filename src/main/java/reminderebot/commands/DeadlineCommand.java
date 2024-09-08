package reminderebot.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import reminderebot.ReminderebotException;
import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;
import reminderebot.task.Deadline;

/**
 * The DeadlineCommand class represents a command to create a Deadline.
 */
public class DeadlineCommand extends Command {
    private final String command;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");

    /**
     * Create a DeadlineCommand.
     * @param command
     */
    public DeadlineCommand(String command) {
        this.command = command;
    }

    /**
     * Creates a Deadline in tasklist.
     * @param tasklist
     * @param ui
     * @param storage
     * @return String representing Deadline command
     * @throws ReminderebotException
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException {
        String[] dlInfo = command.split("/by ");
        try {
            LocalDateTime byTime = LocalDateTime.parse(dlInfo[1].trim(), formatter);
            Deadline deadline = new Deadline(dlInfo[0], byTime);
            tasklist.addTask(deadline);
            return ui.addTask(deadline, tasklist.length());
        } catch (IllegalArgumentException e) {
            // if datetime not in correct format
            throw new ReminderebotException("/by <datetime> should be of format dd/MM/yy HHmm");
        }
    }

    /**
     * Deadline does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
