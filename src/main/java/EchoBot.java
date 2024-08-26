import echo.Parser;
import echo.Storage;
import echo.TaskList;
import echo.Ui;

import java.util.Scanner;

public class EchoBot {
    private Scanner scanner = new Scanner(System.in);
    private Ui ui = new Ui();
    private TaskList allTasks;

    public EchoBot() {
        Storage.init();
        this.allTasks = Storage.getData();
        this.ui.greet();
    }

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
