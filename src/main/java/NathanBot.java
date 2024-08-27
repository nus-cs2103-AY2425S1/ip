import storage.Storage;
import tasks.TaskList;
import ui.UserInterface;

public class NathanBot {
    // cleaned up using Copilot

    public static void main(String[] args) {
        Storage storage = new Storage("./data", "./data/TaskList.txt", "./data/TaskList.dat");
        TaskList taskList = new TaskList(storage);
        UserInterface ui = new UserInterface(taskList);
        ui.start();
    }

    
}