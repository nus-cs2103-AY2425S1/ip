import java.util.ArrayList;
import java.util.Scanner;

public class ChatBaby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> textEntered = new ArrayList<>();
        greet();

        String input = scanner.nextLine();
        while (true) {
            if (input.equals("bye")) {
                bye();
                break;
            }

            else if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < textEntered.size(); i++) {
                    System.out.println((i + 1) + ". " + textEntered.get(i));
                }
                System.out.println("\n____________________________________________________________");
            }

            else {
                textEntered.add(input);
                System.out.println("____________________________________________________________\n"
                        + "added: " + input + "\n"
                        + "____________________________________________________________");
            }

            input = scanner.nextLine();
        }
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
