import nathanbot.storage.Storage;
import nathanbot.tasks.TaskList;
import nathanbot.ui.UserInterface;

public class NathanBot {
    public static void main(String[] args) {
        Storage storage = new Storage("./data", "./data/TaskList.txt", "./data/TaskList.dat");
        TaskList taskList = new TaskList(storage);
        UserInterface ui = new UserInterface(taskList);
        ui.start();
    }

    
}