import java.util.Scanner;

public class YapperBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputHistory = new String[100];
        int numTask = 0;

        System.out.println("________________________________");
        System.out.println("Hello! I'm YapperBot");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println("________________________________");
                for (int i = 0; i < numTask; i++) {
                    System.out.println((i + 1) + ". " + inputHistory[i]);
                }
                System.out.println("________________________________");
            } else {
                inputHistory[numTask] = userInput;
                numTask++;
                System.out.println("________________________________");
                System.out.println("added: " + userInput);
                System.out.println("________________________________");
            }
        }

        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
