import java.util.Scanner;

public class Ruby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greeting = """
                Hello! I'm Ruby
                What can I do for you?
                """;
        System.out.println(greeting);

        while (true) {
            String command = scanner.nextLine();
            System.out.println(command);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
