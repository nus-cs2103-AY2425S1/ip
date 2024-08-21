import java.util.Scanner;

public class Arts {
    public static void main(String[] args) {
        String logo = "     _    _____  _______  _____  \n"
                + "    / \\  |  __ \\|__   __|/ ____| \n"
                + "   / _ \\ | |__) |  | |  | (___   \n"
                + "  / ___ \\|  _  /   | |   \\___ \\  \n"
                + " /_/   \\_\\_| \\_\\   |_|   |_____/ \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Arts, your go-to Chatbot.");
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(input);
                System.out.println("Wow, that's interesting! Tell me more!");
                System.out.println("____________________________________________________________");
            }
        }
    }
}

