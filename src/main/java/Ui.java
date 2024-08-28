import java.util.Scanner;

public class Ui {
    private final Scanner inp;

    public Ui() {
        this.inp = new Scanner(System.in);
    }

    private static String formatMsg(String msg) {
        return "____________________________________________________________\n" +
                msg +
                "____________________________________________________________\n";
    }

    public void display(String text) {
        System.out.println(formatMsg(text));
    }

    public void greet() {
        String msg = """
                 Hello! I'm Astra.
                 What can I do for you?
                """;
        display(msg);
    }

    public void goodbye() {
        String msg = " Bye. Hope to see you again soon!\n";
        display(msg);
    }

    public void showError(AstraException error) {
        display(error.getMessage() + '\n');
    }

    public String readCommand() {
        return this.inp.nextLine();
    }

    public void stop() {
        this.inp.close();
    }
}
