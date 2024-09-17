package ScoobyDoo.Command;

import java.time.LocalDateTime;

import ScoobyDoo.UI.UI;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.Deadline;
import ScoobyDoo.task.Task;
import ScoobyDoo.task.TaskList;

public class DeadlineCommand extends Command{
    private final String description;
    private final LocalDateTime byDateTime;
    public DeadlineCommand (String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        Task newDeadline  = new Deadline(description, byDateTime);
        String addTaskMsg = taskList.addTask(newDeadline);
        storage.updateFile(newDeadline.toFileFormatString());
        return ui.response(addTaskMsg);
    }
}
