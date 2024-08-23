import java.util.Scanner;

public class ChatBaby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            input = scanner.nextLine();
        }

        bye();
    }

    public static void greet() {
        System.out.println("____________________________________________________________\n"
                + "Hello! I'm ChatBaby\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
    }

    public static void bye() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________");
    }

    public static void echo(String input) {
        System.out.println("____________________________________________________________\n"
                + input + "\n"
                + "____________________________________________________________");
    }
}
