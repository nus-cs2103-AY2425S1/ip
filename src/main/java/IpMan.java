import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IpMan {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final List<String> items = new ArrayList<>();

    private static void printItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, items.get(i));
        }
    }

    public static void main(String[] args) {
        // Hello
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Ip Man.");
        System.out.println("What can I do for you?");

        // Main loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printItems();
            } else {
                System.out.println(input);
                items.add(input);
            }
        }

        // Goodbye
        System.out.println(HORIZONTAL_LINE);
        System.out.println("That's enough for today. See you another time.");
        System.out.println(HORIZONTAL_LINE);
    }
}
