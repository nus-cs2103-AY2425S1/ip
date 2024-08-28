import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Stobberi {
    private TaskList taskList;
    private Ui ui;
    Storage storage;


    public Stobberi() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage("data/list.txt");
    }

    

    public void run() {
        taskList.updateTaskList(storage.getList());
        ui.greet();
//        createList();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, taskList);
                c.execute();
                isExit = c.isExit();
            } catch (StobberiException e) {
                Ui.displayForm(e.getMessage());
            }
        }
        ui.goodbye();
        storage.saveList(taskList.getListOfTasks());
    }

    public static void main(String[] args) {
        new Stobberi().run();
    }
}
