import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        this.showBar();
        System.out.print("> ");
        String input = scanner.nextLine();
        this.showBar();
        return input;
    }

    public void show(String text) {
        System.out.println(text);
    }

    public void advise() {
        System.out.println(Ui.HELP_MESSAGE);
    }

    public void showBar() {
        System.out.println(Ui.BAR);
    }
    private static final String BAR = "____________________________________________________________";

    private static final String HELP_MESSAGE = "Key in \"I need help.\" for additional help.";
}
