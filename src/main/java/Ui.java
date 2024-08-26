import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "    ____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showError(DumplingException exception) {
        this.echo("     " + exception.getMessage());
    }

    public void showLine() {
        this.echo(DIVIDER);
    }

    public void showWelcome() {
        this.showLine();
        String greetingMessage = "    Hello! I'm Dumpling\n" +
                "    What can I do for you?";
        this.echo(greetingMessage);
        this.showLine();
    }

    public void exit() {
        String exitMessage = "    Bye. Hope to see you again soon!";
        this.echo(exitMessage);
        this.scanner.close();
        this.showLine();
    }

    public void echo(String message) {
        System.out.println(message);
    }
}
