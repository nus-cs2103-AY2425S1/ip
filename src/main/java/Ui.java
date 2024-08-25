import java.util.Scanner;

public class Ui {

    private static final String BAR = "____________________________________________________________";

    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(BAR);
        System.out.println("Hello! I'm Lama");
        System.out.println("What can I do for you?");
        System.out.println(BAR + "\n");
    }
}
