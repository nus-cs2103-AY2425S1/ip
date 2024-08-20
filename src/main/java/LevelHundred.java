import java.util.Arrays;
import java.util.List;
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

    private Task createTask(String[] words, String command) {
        if (command.equals("todo")) {
            String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            return new Todo(taskDescription);
        } else if (command.equals("deadline")) {
            List<String> tmp = Arrays.asList(words);
            int byIdx = tmp.indexOf("/by");
            String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, byIdx));
            String by = String.join(" ", Arrays.copyOfRange(words, byIdx + 1, words.length));
            return new Deadline(taskDescription, by);
        } else {
            List<String> tmp = Arrays.asList(words);
            int fromIdx = tmp.indexOf("/from");
            int toIdx = tmp.indexOf("/to");
            String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, fromIdx));
            String from = String.join(" ", Arrays.copyOfRange(words, fromIdx + 1, toIdx));
            String to = String.join(" ", Arrays.copyOfRange(words, toIdx + 1, words.length));
            return new Event(taskDescription, from, to);
        }
    }

    private void run() {
        this.ui.greet(this.name);

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String userInput = "";

        while (isRunning) {
            userInput = sc.nextLine();
            String[] words = userInput.split(" ");
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
                case "todo": case "deadline": case "event":
                    Task newTask = createTask(words, command);
                    this.storage.addTask(newTask);
                    this.ui.printAddTask(newTask);
                    break;
                default:

            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        LevelHundred chatbot = new LevelHundred();
        chatbot.run();
    }
}
