package cypherchatbot.command;

import cypherchatbot.task.Event;
import cypherchatbot.task.Task;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command {

    String[] command;

    public EventCommand(String[] s) {
        this.command = s;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDateTime from = LocalDateTime.parse(command[1].trim(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime to = LocalDateTime.parse(command[2].trim(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Task task = new Event(command[0], from, to);
            String output = tasks.addToList(task);
            ui.output(output);
            storage.addToStorage(task.toStringinFile());
        } catch (DateTimeException e) {
            ui.showError("Enter a valid date and time in the format of yyyy-MM-dd HH:mm");
        }
    };

    public boolean isExit() {
        return false;
    }
}
