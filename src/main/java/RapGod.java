import java.util.Scanner;

public class RapGod {
    public static void main(String[] args) {
        String logo = """
                ██████╗  █████╗ ██████╗  ██████╗  ██████╗ ██████╗\s
                ██╔══██╗██╔══██╗██╔══██╗██╔════╝ ██╔═══██╗██╔══██╗
                ██████╔╝███████║██████╔╝██║  ███╗██║   ██║██║  ██║
                ██╔══██╗██╔══██║██╔═══╝ ██║   ██║██║   ██║██║  ██║
                ██║  ██║██║  ██║██║     ╚██████╔╝╚██████╔╝██████╔╝
                ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝      ╚═════╝  ╚═════╝ ╚═════╝\s
                                                                 \s                      
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
                Type:
                'Echo' to echo
                'List' to create a list
                'Bye' to exit
                -----------------------------------------------
                """;

        System.out.println(header);
        System.out.println(logo);
        System.out.print(introduction);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("Option: ");
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("echo")) {
                Echo.run();
                break;
            } else if (option.equalsIgnoreCase("list")) {
                List.run();
                break;
            } else if (option.equalsIgnoreCase("Bye")) {
                System.out.println("-----------------------------------------------");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("-----------------------------------------------");
            } else {
                System.out.println("Option does not exist. Try again.");
            }
        }

    }
}
