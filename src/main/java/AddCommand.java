import java.util.ArrayList;

public abstract class AddCommand extends Command {

    TaskManager taskManager;
    FileManager fileManager;
    String userInput;
    ArrayList<Task> tasks;
    public AddCommand(String userInput, TaskManager taskManager, FileManager fileManager) {
        this.userInput = userInput;
        this.taskManager = taskManager;
        this.fileManager = fileManager;
        this.tasks = taskManager.getTasksArray();
    }
}
