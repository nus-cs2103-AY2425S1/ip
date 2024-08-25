import java.util.ArrayList;
import java.util.Scanner;

public class EchoBot {
    private Scanner scanner = new Scanner(System.in);
    private Ui ui = new Ui();
    private TaskList allTasks;

    public EchoBot() {
        Data.init();
        this.allTasks = Data.getData();
        this.ui.greet();
    }

    public void run() {
        boolean isExit = false;
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
