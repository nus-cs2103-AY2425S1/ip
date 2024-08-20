import java.util.logging.Level;
import java.util.Scanner;

public class LevelHundred {
    private final String name = "LevelHundred";
    private final Ui ui;

    public LevelHundred() {
        this.ui = new Ui();
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
                default:
                    this.ui.echo(userInput);
            }
        }

        sc.close();
    }
    public static void main(String[] args) {
        LevelHundred chatbot = new LevelHundred();
        chatbot.run();
    }
}
