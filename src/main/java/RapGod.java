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
                Type 'Bye' to exit.
                -----------------------------------------------
                """;

        System.out.println(header);
        System.out.println(logo);
        System.out.print(introduction);

        Scanner scanner = new Scanner(System.in);
        String input = "";
        int count = 1;

        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("Bye")) {
                System.out.println("-----------------------------------------------");
                System.out.printf("RapGod: %s, yo!\n", input);
                System.out.println("-----------------------------------------------");

                count++;
            } else {
                break;
            }

        }

        System.out.println("-----------------------------------------------");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("-----------------------------------------------");
    }
}
