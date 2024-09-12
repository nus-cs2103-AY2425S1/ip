package jag;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to encapsulate Updating a given task
 */
public class UpdateCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String description;
        Task task = tasks.getTask(ui.getUpdateIndex() - 1);
        description = ui.getDescription('U');

        // Check instance of task and update accordingly
        if (task instanceof Todo) {
            ((Todo) task).update(description);
        } else if (task instanceof Deadline) {
            String by = ui.getBy();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime d1 = LocalDateTime.parse(by, formatter);
            ((Deadline) task).update(description, d1);
        } else if (task instanceof Event) {
            String from = ui.getFrom();
            String to = ui.getTo();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime d1 = LocalDateTime.parse(from, formatter);
            LocalDateTime d2 = LocalDateTime.parse(to, formatter);
            ((Event) task).update(description, d1, d2);
        } else {
            ui.showError("Task not found");
        }

        // jag.Storage
        try {
            storage.write(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }

        ui.updateResponse(task);
    }

    /**
     * Checks whether Command is an instance of Exit Command
     * @return False
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
