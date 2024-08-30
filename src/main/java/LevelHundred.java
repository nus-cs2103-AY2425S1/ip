import java.util.Scanner;
import java.util.ArrayList;


public class LevelHundred {
    private final String NAME = "LevelHundred";
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    
    public LevelHundred() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    private void initialiseTaskList() {
        try {
            ArrayList<Task> tasks = this.storage.load();
            tasks.forEach(t -> this.taskList.addTask(t));
        } catch (InvalidStorageFileException e) {
            this.ui.printException(e);
        }
    }

    private void run() {
        this.initialiseTaskList();

        this.ui.greet(this.NAME);

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String userInput = "";

        while (isRunning) {
            userInput = sc.nextLine();
            try {
                UserCommand c = Parser.parseUserCommand(userInput);
                c.execute(userInput, this.ui, this.storage, this.taskList);
                isRunning = c.continueRunning();
            } catch (LevelHundredException e) {
                this.ui.printException(e);
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        LevelHundred chatbot = new LevelHundred();
        chatbot.run();
    }
}
