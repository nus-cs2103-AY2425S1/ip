import java.util.Scanner;

public class YapperBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("________________________________");
        System.out.println("Hello! I'm YapperBot");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            }

            System.out.println("________________________________");
            System.out.println(userInput);
            System.out.println("________________________________");
        }

        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
