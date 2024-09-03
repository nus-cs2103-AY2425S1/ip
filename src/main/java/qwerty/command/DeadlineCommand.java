package qwerty.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.task.Deadline;
import qwerty.task.Task;
import qwerty.ui.Ui;

/**
 * This class encapsulates a 'deadline' command.
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Creates a new Deadline task and adds it to the TaskList.
     *
     * @param tasks The TaskList component handling storage of Task objects.
     * @param ui The Ui component handling user interaction.
     * @param storage The Storage component handling read/write to hard disk.
     * @throws QwertyException If arguments are missing or date arguments are not formatted correctly.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws QwertyException {
        // Check whether arguments exist
        String description = getArgs().get("main");
        String strBy = getArgs().get("by");
        if (description == null) {
            throw new QwertyException("Your task is missing a name.");
        }
        if (strBy == null) {
            throw new QwertyException("You didn't tell me when your deadline is.");
        }

        try {
            // Parse arguments into a LocalDateTime object
            LocalDateTime by = LocalDateTime.parse(
                    strBy, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            Task deadline = new Deadline(description, by);

            // Add the task to the task list
            tasks.addTask(deadline);

            ui.showQwertyMessage("\nAdded this task:\n" + deadline
                    + "\nNow you have " + tasks.getSize() + (tasks.getSize() == 1 ? " task " : " tasks ")
                    + "in the list.\nBetter get to it.");
        } catch (DateTimeParseException e) {
            ui.showError("I don't like the way you write dates.\nUse this format: dd/MM/yyyy HHmm");
        }
    }

}
