import java.util.logging.Level;

public class LevelHundred {
    private final String name = "LevelHundred";
    private final Ui ui;

    public LevelHundred() {
        this.ui = new Ui();
    }

    private void greet() {
        this.ui.printHorizontalLine();
        System.out.println("Hello, I'm " + this.name);
        System.out.println("What can I do for you?");
        this.ui.printHorizontalLine();
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        ui.printHorizontalLine();
    }
    public static void main(String[] args) {
        LevelHundred chatbot = new LevelHundred();
        chatbot.greet();
        chatbot.exit();

    }
}
