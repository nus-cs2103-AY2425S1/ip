package bob.command;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.tasks.Deadline;
import bob.tasks.Event;
import bob.ui.Ui;
import bob.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UpdateCommand extends Command {
    private int index;
    private String newDetails;

    public UpdateCommand(int index, String newDetails) {
        this.index = index;
        this.newDetails = newDetails;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (index < 0 || index >= tasks.size()) {
            throw new BobException("Invalid task index");
        }

        Task task = tasks.get(index);
        if (task instanceof Event) {
            String[] details = newDetails.split(" ", 2);
            ((Event) task).updateEndTime(details[1]);
        } else if (task instanceof Deadline) {
            LocalDateTime newDeadline = LocalDateTime.parse(newDetails, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            ((Deadline) task).updateDeadline(newDeadline);
        } else {
            throw new BobException("Unsupported task type for update");
        }

        storage.save(tasks);
        return ui.showUpdatedTask(task);
    }
}

