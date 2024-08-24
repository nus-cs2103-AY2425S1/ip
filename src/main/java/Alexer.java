import java.util.Scanner;

public class Alexer {
    private static final String GOODBYE = "Goodbye! If you ever want to chat again, I'll be here.\nHave a great day!";
    private static final String BREAK = "____________________________________________________________";

    public static final String NAME = "Alexer";

    private final Scanner scanner = new Scanner(System.in);

    public void promptLoop() {
        String input = scanner.nextLine();

        if (!input.equals("bye")) {
            System.out.println(BREAK);
            System.out.println(input);
            System.out.println(BREAK);
            promptLoop();
        } else {
            System.out.println(BREAK);
            System.out.println(GOODBYE);
            System.out.println(BREAK);
        }
    }
    public static void main(String[] args) {
        String logo = """
                     .     .                           \s
                    /|     |     ___  _  .-   ___  .___\s
                   /  \\    |   .'   `  \\,'  .'   ` /   \\
                  /---'\\   |   |----'  /\\   |----' |   '
                ,'      \\ /\\__ `.___, /  \\  `.___, /   \s""";

        System.out.println(BREAK);
        System.out.println(logo);
        System.out.println(BREAK);
        System.out.printf("Hello from %s, what can I do for you today?\n", NAME);
        System.out.println(BREAK);
        System.out.println(GOODBYE);
        System.out.println(BREAK);
    }
}
