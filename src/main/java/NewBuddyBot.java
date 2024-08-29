import java.io.IOException;
import java.util.ArrayList;

public class NewBuddyBot {
    private FileStorage storage;
    private TaskList taskList;
    private Ui ui;

//    public NewBuddyBot(String filePath) {
//        this.taskList = new TaskList();
//        this.ui = new Ui();
//        this.storage = new FileStorage(filePath);
//        try {
//            ArrayList<Task> tasks = storage.load();
//            for (Task task: tasks) {
//                taskList.addTask(task);
//            }
//            ui.showSuccessMsg(tasks.size());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /*public void run() {
        ui.welcomeMsg("BuddyBot");

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (BuddyBotException e) {
                ui.showLoadingError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.goodbyeMsg();
    }*/
}
