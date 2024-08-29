package Bob.Command;

import Bob.Exception.BobException;
import Bob.Storage.Storage;
import Bob.Tasks.Deadline;
import Bob.Tasks.Task;
import Bob.Ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DeadlineCommand implements Command {
    private final String taskDescription;

    public DeadlineCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
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
            ui.showAddedTask(deadline, tasks.size());
        } catch (DateTimeParseException e) {
            throw new BobException("Invalid date format! Please use dd/MM/yyyy HHmm :(");
        }
    }
}
