package qwerty.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.Ui;
import qwerty.task.Task;
import qwerty.task.Deadline;

public class DeadlineCommand extends Command {

    public DeadlineCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

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

            ui.showMessage("\nAdded this task:\n" + deadline
                    + "\nNow you have " + tasks.getSize() + (tasks.getSize() == 1 ? " task " : " tasks ")
                    + "in the list.\nBetter get to it.");
        } catch (DateTimeParseException e) {
            ui.showError("I don't like the way you write dates.\nUse this format: dd/MM/yyyy HHmm");
        }
    }

}
