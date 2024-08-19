import java.util.Objects;
import java.util.Scanner;

public class Darwin {
    static final String NAME = "Darwin";
    static final String END = "bye";

    private static void reply(String in) {
        System.out.println(in);
        String line = "-".repeat(30);
        System.out.println(line);
    }

    public static void main(String[] args) {
        String startMsg = String.format("Hello! I'm %s\nWhat can I do for you?", NAME);
        String endMsg = "Bye. Hope to see you again soon!";

        reply(startMsg);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String in = scanner.nextLine();
            if (in.equals(END)) {
                reply(endMsg);
                break;
            }
            reply(in);
        }
        scanner.close();
    }
}
