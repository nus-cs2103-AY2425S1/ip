import java.util.logging.Level;
import java.util.Scanner;

public class LevelHundred {
    private final String name = "LevelHundred";
    private final Ui ui;

    public LevelHundred() {
        this.ui = new Ui();
    }

    private void greet() {
        this.ui.printHorizontalLine();
        System.out.println("\t" + "Hello, I'm " + this.name);
        System.out.println("\t" + "What can I do for you?");
        this.ui.printHorizontalLine();
    }

    private void exit() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        ui.printHorizontalLine();
    }

    private void run() {
        this.greet();
        Scanner sc = new Scanner(System.in);
        String END = "bye";
        String userInput = sc.nextLine();

        while (!userInput.equals(END)) {
            this.ui.echo(userInput);
            userInput = sc.nextLine();
        }

        this.exit();
    }
    public static void main(String[] args) {
        LevelHundred chatbot = new LevelHundred();
        chatbot.run();
    }
}
