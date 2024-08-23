import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RapGod {

    public static Scanner scanner = new Scanner(System.in);
    public static final ArrayList<String> RUDE_WORDS = new ArrayList<>(Arrays.asList(
            "damn", "hell", "shit", "fuck",
            "bitch", "asshole", "dickhead",
            "idiot", "moron", "stupid",
            "loser", "jerk", "creep"
    ));
    public static void main(String[] args) {
        String logo = """
                  ____              ____           _\s
                |  _ \\ __ _ _ __  / ___| ___   __| |
                | |_) / _` | '_ \\| |  _ / _ \\ / _` |
                |  _ < (_| | |_) | |_| | (_) | (_| |
                |_| \\_\\__,_| .__/ \\____|\\___/ \\__,_|
                           |_|                     \s
                """;

        String header = """
                -----------------------------------------------
                 I'm beginning to feel like a:
                """;

        String introduction = """
                -----------------------------------------------
                Hello. I'm RapGod
                What can I do for you today?
                
                [OPTIONS]
                'Echo bot' to echo
                'List bot' to create a list
                'Bye' to exit
                -----------------------------------------------
                """;

        System.out.println(header);
        System.out.println(logo);
        System.out.print(introduction);

        while(true) {
            System.out.print("Option:\n");
            String option = scanner.nextLine();

            try {
                if (option == null || option.trim().isEmpty()) {
                    throw new NoInputException();
                } else if (RapGod.RUDE_WORDS.contains(option)) {
                    throw new RudeInputException();
                }
            } catch (NoInputException | RudeInputException exc) {
                System.out.println("-----------------------------------------------");
                System.out.println("RapGod:\n" + exc.getMessage());
                System.out.println("-----------------------------------------------");
                continue;
            }

            if (option.equalsIgnoreCase("echo bot")) {
                Echo.run();
                break;
            } else if (option.equalsIgnoreCase("list bot")) {
                List.run();
                break;
            } else if (option.equalsIgnoreCase("Bye")) {
                System.out.println("-----------------------------------------------");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("-----------------------------------------------");
                break;
            } else {
                System.out.println("Option does not exist. Try again.");
            }

        }

    }
}
