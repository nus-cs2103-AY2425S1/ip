import echo.Parser;
import echo.Storage;
import echo.TaskList;
import echo.Ui;

import java.util.Scanner;

/**
 * Represents a simple task management bot that interacts with the user via the console.
 * It handles task storage, user input, and displays task-related information.
 */
public class EchoBot {
    private Scanner scanner = new Scanner(System.in);
    private Ui ui = new Ui();
    private TaskList allTasks;

    public EchoBot() {
        Storage.init();
        this.allTasks = Storage.getData();
        this.ui.greet();
    }

    /**
     * Runs the main function of the EchoBot.
     * Continuously reads users' input, processes the input to perform actions
     * on the task list, and displays results until the exit condition is met.
     */
    public void run() {
        boolean isExit = false;

        // parse the user input iteratively
        while (!isExit) {
            String userInput = this.scanner.nextLine();
            ui.showLine();
            isExit = Parser.parse(userInput, this.allTasks);
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        EchoBot bot = new EchoBot();
        bot.run();
    }
}
