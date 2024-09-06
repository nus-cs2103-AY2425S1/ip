package bob.Command;

import bob.Exception.BobException;
import bob.Storage.Storage;
import bob.Tasks.Deadline;
import bob.Tasks.Task;
import bob.Ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private final String taskDescription;

    public DeadlineCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskDescription.isEmpty()) {
            throw new BobException("Missing deadline description :(");
        }
        String[] dlParts = taskDescription.split(" /by ");
        if (dlParts.length < 2) {
            throw new BobException("Missing details :(\nPlease use this format: 'deadline [description] /by [dd/MM/yyyy HHmm]' ");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime deadlineDateTime = LocalDateTime.parse(dlParts[1], formatter);

            Task deadline = new Deadline(dlParts[0], deadlineDateTime);
            tasks.add(deadline);
            storage.save(tasks);
            return ui.showAddedTask(deadline, tasks.size());
        } catch (DateTimeParseException e) {
            throw new BobException("Invalid date format! Please use dd/MM/yyyy HHmm :(");
        }
    }
}
