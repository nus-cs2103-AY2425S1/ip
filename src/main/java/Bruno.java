import java.util.Scanner;

public class Bruno {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userResponse;

        printGreetingMessage();

        boolean running = true;
        while (running) {
            userResponse = input.nextLine();
            if (userResponse.equals("bye")) {
                running = false;
                printByeMessage();
            } else {
                echo(userResponse);
            }
        }
    }

    public static void printGreetingMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bruno\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void echo(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }
}
