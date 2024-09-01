package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;
import king.task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        String[] parts = input.split(" /from | /to ");
        if (parts.length == 3) {
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            try {
                LocalDateTime fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                LocalDateTime toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                Event event = new Event(description, fromDate, toDate);
                tasks.add(event);
                ui.showTaskAdded(event, tasks.size());
                storage.save(tasks.getTaskList());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid event date format. Please use yyyy-MM-dd HHmm.");
            }
        } else {
            System.out.println("Invalid event date format. Please use yyyy-MM-dd HHmm.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
