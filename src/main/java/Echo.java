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

            try {
                if (input == null || input.trim().isEmpty()) {
                    throw new NoInputException();
                } else if (RapGod.RUDE_WORDS.contains(input)) {
                    throw new RudeInputException();
                }
            } catch (NoInputException | RudeInputException exc) {
                System.out.println("-----------------------------------------------");
                System.out.println("RapGod:\n" + exc.getMessage());
                System.out.println("-----------------------------------------------");
                continue;
            }

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
