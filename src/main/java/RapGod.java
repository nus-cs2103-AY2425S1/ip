import java.util.Scanner;

public class RapGod {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = """
                 I'm beginning to feel like a
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
                -----------------------------------------------
                """;

        String introduction = """
                -----------------------------------------------
                Hello. I'm RapGod
                What can I do for you today?
                
                [OPTIONS]
                'Echo' to echo
                'Make List' to create a list
                'Bye' to exit
                -----------------------------------------------
                """;

        System.out.println(header);
        System.out.println(logo);
        System.out.print(introduction);

        while(true) {
            System.out.print("Option:\n");
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("echo")) {
                Echo.run();
                break;
            } else if (option.equalsIgnoreCase("make list")) {
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
