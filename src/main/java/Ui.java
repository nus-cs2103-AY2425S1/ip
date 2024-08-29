import java.util.Scanner;

public class Ui {
    private String name = "Pixel";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showWelcome() {
        PixelSays("Hello! I'm " + name, "What can I do for you?\n");
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void PixelSays(String... args) {
        for (String arg : args) {
            System.out.println("    " + arg);
        }
    }

    public void closeUi() {
        this.scanner.close();
    }
}
