import java.util.Scanner;

public class XBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println("Hello! I'm XBot\n" + "What can I do for you?\n");
        while (!input.equalsIgnoreCase("Bye") || !input.equalsIgnoreCase("bye")) {
            System.out.println(input);
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
