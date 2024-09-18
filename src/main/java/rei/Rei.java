package rei;

import java.time.LocalDateTime;
import java.util.Scanner;


/**
 * Main class for REI bot.
 */
public class Rei {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a REI instance
     * @param filePath where to load the stored data
     */
    public Rei(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());

    }

    /**
     * Runs the bot
     */
    public void run() {
        Ui.printWelcomeMessage();
        tasks.printTasks();

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        int taskIndex;
        while (!isExit) {
            String prompt = scanner.nextLine();
            Parser.Prompt promptType = Parser.parse(tasks, prompt);
            switch (promptType) {
                case LIST:
                    tasks.printTasks();
                    break;
                case MARK:
                    taskIndex = Integer.parseInt(prompt.substring(4).trim());
                    tasks.markTask(taskIndex);
                    break;
                case UNMARK:
                    taskIndex = Integer.parseInt(prompt.substring(6).trim());
                    tasks.unmarkTask(taskIndex);
                    break;
                case TODO:
                    tasks.addTask(Task.createToDo(prompt.substring(5)));
                    break;
                case DEADLINE:
                    tasks.addTask(Task.createDeadline(prompt.substring(9, prompt.indexOf("/by")),
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/by") + 4))));
                    break;
                case EVENT:
                    tasks.addTask(Task.createEvent(prompt.substring(6, prompt.indexOf("/from")),
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to") - 1)),
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/to") + 4))));
                    break;
                case DELETE:
                    taskIndex = Integer.parseInt(prompt);
                    tasks.deleteTask(taskIndex);
                    break;
                case FIND:
                    String keyword = prompt.substring(5).trim();
                    tasks.findTasks(keyword);
                    break;
                case ANNYEONG:
                    isExit = true;
                    break;
                case UNKNOWN:
                    // do nothing
                    break;
                default:
                    //do nothing
                    break;
            }
            storage.save(tasks);
        }

        scanner.close();

    }

    /**
     * Runs the bot.
     * Loads database if exist.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        new Rei("/Users/macbookpro/Documents/CS2103T/rei/rei.txt").run();
    }

}
