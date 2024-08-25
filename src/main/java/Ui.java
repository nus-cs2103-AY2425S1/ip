import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "===================================================";
    private static final String GREETING_MESSAGE = "  ∧,,,∧\n( ̳̳• · ̳• )\n づ Meow! I'm Neko\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon meow ฅ ฅ";
    private static final Scanner scanner = new Scanner(System.in);
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    public String getUserCommand() {
        out.println("Enter command: ");
        return in.nextLine();
    }
    public String getInput() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        out.println(message + "\n" + DIVIDER);
    }

    public void showMessageWODivier(String message) {
        out.println(message);
    }

    public void showGreeting() {
        showMessage(GREETING_MESSAGE);
    }

    public void showExitMessage() {
        showMessage(EXIT_MESSAGE);
    }

    public String getTaskType() {
        out.println("What kind of task would you like to add today?\n  1: Todo (Just a simple task meow)\n" +
                "  2: Deadline (Something with a time limit meow)\n  3: Event (A task with a start and end time meow)\n" +
                "Please enter the number of the task type you'd like to add meow~");
        return in.nextLine().trim();
    }

    public String getTaskName() {
        out.println("What will this task be called meow?");
        return in.nextLine().trim();
    }

    public LocalDateTime getDateTime(String prompt) {
        out.println(prompt + " (e.g., 2024-01-01T13:00 for January 1, 2024, at 1:00 PM)");
        while (true) {
            String input = in.nextLine().trim();
            try {
                return Parser.parseTime(input);
            } catch (DateTimeParseException e) {
                out.println("Meow /ᐠ > ˕ <マ Invalid date/time format ! Please use 'yyyy-MM-ddTHH:mm' format.");
            }
        }
    }

}
