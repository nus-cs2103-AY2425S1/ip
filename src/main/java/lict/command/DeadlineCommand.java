package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.task.Task;
import lict.task.Deadline;
import lict.LictException;

public class DeadlineCommand extends Command {
    private String info;

    public DeadlineCommand(String info) {
        this.info = info;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        String[] messageParts = info.split("/by", 2);
        String description = messageParts[0].trim();
        if (description.isEmpty()) {
            throw new LictException("OOPS!!! The description of a deadline cannot be empty. Please ensure that your input is in the format: deadline {description} /by {your deadline}");
        }
        if (messageParts.length != 2) {
            throw new LictException("OOPS!!! The deadline needs to be indicated. Please ensure that your input is in the format: deadline {description} /by {your deadline}");
        }
        Task newTask = new Deadline(description, messageParts[1].trim());;
        tasks.addTask(newTask);
        ui.hasAddedTask(newTask, tasks.size());
        storage.save(tasks);
    }
}
