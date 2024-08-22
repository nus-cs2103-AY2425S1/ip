import java.util.Scanner;

public class Elysia {
    static String line = "____________________________________________________________";
    static String welcomeMessage = "Hello! I'm Elysia\nWhat can I do for you?";
    static String exitMessage = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(line);
        System.out.println(welcomeMessage);
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }

            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        System.out.println(exitMessage);
        System.out.println(line);
    }
}
