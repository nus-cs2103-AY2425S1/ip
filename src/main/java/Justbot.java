import java.util.Scanner;

public class Justbot {
    public static void main(String[] args) {
        final String chatbotName = "JustBot";

        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------");
        System.out.println("Hello I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
        String input = "";
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("------------------------------------------");
                break;
            }
            System.out.println("------------------------------------------");
            System.out.println(input);
            System.out.println("------------------------------------------");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");

    }

}
