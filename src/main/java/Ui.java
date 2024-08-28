import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public String getUserInput() {
        out.print("Enter your command: ");
        return in.nextLine();
    }

    public void printBye() {
        out.print("""
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """);
    }

    public void printWelcome() {
        out.print("""
                ____________________________________________________________
                Hello! I'm Chatterbox
                Below is your current list!
                What can I do for you?
                ____________________________________________________________
                """);
    }

    public void printTasks(StoredList taskList) {
        out.print(taskList.toString());
    }

    public void printMessage(String message) {
        out.println(message);
    }
}
