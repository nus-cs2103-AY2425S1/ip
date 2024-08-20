import java.util.logging.Level;
import java.util.Scanner;
import java.util.ArrayList;

public class LevelHundred {
    private final String name = "LevelHundred";
    private final Ui ui;
    private final Storage storage;

    public LevelHundred() {
        this.ui = new Ui();
        this.storage = new Storage();
    }

    private void run() {
        this.ui.greet(this.name);

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String userInput = "";

        while (isRunning) {
            userInput = sc.nextLine();
            switch(userInput) {
                case "bye":
                    isRunning = false;
                    this.ui.exit();
                    break;
                case "list":
                    ArrayList<Task> tasks = this.storage.getTaskList();
                    this.ui.printTasks(tasks);
                    break;
                default:
                    Task newTask = new Task(userInput);
                    this.storage.addTask(newTask);
                    this.ui.printAddTask(newTask);
            }
        }

        sc.close();
    }
    public static void main(String[] args) {
        LevelHundred chatbot = new LevelHundred();
        chatbot.run();
    }
}
