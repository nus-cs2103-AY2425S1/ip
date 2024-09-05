package rei;

import java.util.Scanner;



/**
 * Main class for Rei bot.
 */
public class Rei {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Rei(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());

    }

    public void run() {
        Ui.welcome();
        tasks.printTasks();

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String prompt = scanner.nextLine();
            isExit = Parser.parse(tasks, prompt);
        }

        scanner.close();
        storage.save(tasks);
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
