package derek.command;

import derek.Storage;
import derek.exception.TaskNotFoundException;
import derek.Ui;
import derek.task.Task;
import derek.task.TaskList;

public class FindCommand extends Command {

    private String taskDescription;
    public FindCommand(String command) {
        super(command);

    }

    public void getTaskDescription() {
        String command = super.getCommand();
        int firstWord = command.indexOf(" ");
        this.taskDescription = command.substring(firstWord + 1);

    }

    public void execute(Storage storage, Ui ui) {
        try {
            TaskList taskList = storage.getTaskList();
            this.getTaskDescription();
            Task task = taskList.find(this.taskDescription);
            ui.printTask(task);
        } catch (TaskNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
