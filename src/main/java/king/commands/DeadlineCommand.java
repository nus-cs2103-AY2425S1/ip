package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;
import king.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        String[] parts = this.input.split(" /by ");
        if (parts.length == 2) {
            String description = parts[0].trim();
            String by = parts[1].trim();
            try {
                LocalDateTime byDate = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                Deadline deadline = new Deadline(description, byDate);
                tasks.add(deadline);
                ui.showTaskAdded(deadline, tasks.size());
                storage.save(tasks.getTaskList());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid event date format. Please use yyyy-MM-dd HHmm.");
            }
        } else {
            throw new KingException("Invalid event date format. Please use yyyy-MM-dd HHmm.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

