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
            String[] words =userInput.split(" ");
            String command = words[0];
            switch(command) {
                case "bye":
                    isRunning = false;
                    this.ui.exit();
                    break;
                case "list":
                    ArrayList<Task> tasks = this.storage.getTaskList();
                    this.ui.printTasks(tasks);
                    break;
                case "mark": case "unmark":
                    if (words.length == 1) {
                        this.ui.printFail();
                        break;
                    }
                    try {
                        int idx = Integer.parseInt(words[1]) - 1;
                        Task t = this.storage.get(idx);
                        if (command.equals("mark")) {
                            t.mark();
                            this.ui.printSuccessfulMark(t);
                        } else {
                            t.unmark();
                            this.ui.printSuccessfulUnmark(t);
                        }
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        this.ui.printFail();
                    }
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
