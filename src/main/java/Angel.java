import java.util.Scanner;

public class Angel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        String logo = "____________________________________________________________\n";

        System.out.println(logo + " Hello! I'm Angel\n What can I do for you?\n" + logo);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(logo + " Bye. Hope to see you again soon!\n" + logo);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(logo);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(logo);
            } else {
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println(logo + " added: " + userInput + "\n" + logo);
            }
        }

        scanner.close();
    }
}
