import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bob {
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String GREETINGS = "Hello! I'm Bob\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    
    public static void main(String[] args) {

        System.out.print(DIVIDER + GREETINGS + DIVIDER);

        chatBot();

        System.out.print(EXIT + DIVIDER);
    }

    public static void chatBot() {
        Scanner scanner = new Scanner(System.in);
        List<String> myTasks = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "bye":
                    System.out.print(DIVIDER);
                    return;
                case "list":
                    System.out.print(DIVIDER);
                    for (int i = 1; i < myTasks.size() + 1; i++) {
                        System.out.print(i + ". " + myTasks.get(i-1) + "\n");
                    }
                    System.out.print(DIVIDER);
                    break;
                default:
                    myTasks.add(input);
                    System.out.print(DIVIDER + "added: " + input + "\n" + DIVIDER);

            }
        }
    }
}
