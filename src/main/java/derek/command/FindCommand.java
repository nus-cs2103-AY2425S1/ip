package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.TaskNotFoundException;
import derek.task.Task;
import derek.task.TaskList;

public class FindCommand extends Command {

    private String taskDescription;
    private Storage storage;
    private Ui ui;
    public FindCommand(String command, Storage storage, Ui ui) {
        super(command);
        this.storage = storage;
        this.ui = ui;

    }

    public void getTaskDescription() {
        String command = super.getCommand();
        int firstWord = command.indexOf(" ");
        this.taskDescription = command.substring(firstWord + 1);

    }

    public String execute() {
        try {
            TaskList taskList = storage.getTaskList();
            this.getTaskDescription();
            Task task = taskList.find(this.taskDescription);
            return ui.printTask(task);
        } catch (TaskNotFoundException e) {
           return e.getMessage();
        }
    }
}
