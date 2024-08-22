import java.util.Scanner;

public class Echo {
    public static void run() {

        String initialise = """
                -----------------------------------------------
                Initialising Echo Bot...
                -----------------------------------------------
                """;

        System.out.print(initialise);

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("Bye")) {
                System.out.println("-----------------------------------------------");
                System.out.printf("RapGod: %s, yo!\n", input);
                System.out.println("-----------------------------------------------");

            } else {
                break;
            }

        }

        System.out.println("-----------------------------------------------");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("-----------------------------------------------");
    }
}
