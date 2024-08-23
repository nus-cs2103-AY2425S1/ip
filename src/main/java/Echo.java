import java.util.Scanner;

public class Echo {
    public static void run() {

        String initialise = """
                -----------------------------------------------
                Initialising Echo Bot...
                -----------------------------------------------
                """;

        System.out.print(initialise);

        Scanner scanner = RapGod.scanner;
        String input = "";

        while (true) {
            System.out.print("You:\n");
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("Bye")) {
                System.out.println("-----------------------------------------------");
                System.out.printf("RapGod:\n%s, yo!\n", input);
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
