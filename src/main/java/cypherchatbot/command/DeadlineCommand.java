package cypherchatbot.command;

import cypherchatbot.task.Deadline;
import cypherchatbot.task.Task;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command {
    String[] command;

    public DeadlineCommand(String[] s) {
        this.command = s;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDateTime by = LocalDateTime.parse(command[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Task deadline = new Deadline(command[0], by);
            String output = tasks.addToList(deadline);
            ui.output(output);
            storage.addToStorage(deadline.toStringinFile());

        } catch (DateTimeException e) {
            ui.showError("Enter a valid date and time in the format of yyyy-MM-dd HH:mm");
        }
    };

    public boolean isExit() {
        return false;
    }
}
