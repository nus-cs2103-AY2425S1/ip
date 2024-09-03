package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.exceptions.NoDescriptionExceptions;
import toothless.exceptions.NoTimelineExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.task.Deadline;
import toothless.ui.Ui;

public class DeadlineCommand extends Command {

    private String description;

    public DeadlineCommand(String description) {
        this.description = description;
    }
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if(description.isEmpty()) {
            throw new NoDescriptionExceptions("deadline", "deadline <description> /by <timing>");
        } else if(!description.contains("/by")) {
            throw new NoTimelineExceptions("deadline", "deadline <description> /by <timing>");
        }
        String[] splitDeadline = description.split("/by");
        if(splitDeadline.length != 2) {
            throw new NoTimelineExceptions("deadline", "deadline <description> /by <timing>");
        }
        taskList.addTask(new Deadline(splitDeadline[0], splitDeadline[1]), ui, taskList);
        storage.saveTask(taskList.getList());
    }
}
