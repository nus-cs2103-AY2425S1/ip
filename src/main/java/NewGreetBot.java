import java.io.IOException;

public class NewGreetBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning;

    public NewGreetBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isRunning = true;
        try {
            tasks = new TaskList(storage.load());
            tasks.printTasks();
            storage.saveData(tasks);
        } finally {
            /*catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
        }
    }

    public void run() {
        System.out.println(this.ui.greetUser());


            while (this.isRunning) {

            }

    }

    public static void main(String[] args) {
        new NewGreetBot("data/greetbot.txt").run();
    }
}
