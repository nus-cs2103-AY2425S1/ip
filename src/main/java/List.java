import java.util.ArrayList;
import java.util.Scanner;

public class List {
    public static void run() {

        String initialise = """
                -----------------------------------------------
                Initialising List Bot...
                'View List' to show list
                -----------------------------------------------
                """;

        System.out.print(initialise);

        Scanner scanner = new Scanner(System.in);
        String input = "";
        ArrayList<String> list = new ArrayList<String>();

        while (true) {
            System.out.print("Add item: ");
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("Bye")) {
                if (input.equalsIgnoreCase("View List")) {
                    System.out.println("-----------------------------------------------");
                    System.out.println("Displaying List:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, list.get(i));
                    }
                    System.out.println("-----------------------------------------------");
                } else {
                    System.out.println("-----------------------------------------------");
                    System.out.printf("Added: %s\n", input);
                    System.out.println("-----------------------------------------------");
                    list.add(input);
                }

            } else {
                break;
            }

        }

        System.out.println("-----------------------------------------------");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("-----------------------------------------------");
    }
}
