package command;

import main.TaskList;
import main.Storage;
import main.Ui;

public class AddTaskCommand extends Command {

    private String taskType;

    public AddTaskCommand(String description, String type) {
        super(description);
        this.taskType = type;
    }
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String displayMessage;
        switch (taskType) {
        case "todo":
            displayMessage = ui.displayAddMessage(taskList.addTodo(super.getDescription()), taskList.size());
            break;
        case "event":
            displayMessage = ui.displayAddMessage(taskList.addEvent(super.getDescription()), taskList.size());
            break;
        case "deadline":
            displayMessage = ui.displayAddMessage(taskList.addDeadline(super.getDescription()), taskList.size());
            break;
        default:
            return "I dont understand what you are trying to say :(";
        }
        storage.writeStorage(taskList.getTaskList());
        return displayMessage;
    }
}
