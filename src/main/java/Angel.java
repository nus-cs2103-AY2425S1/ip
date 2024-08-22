import java.util.Scanner;

public class Angel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        boolean[] isDone = new boolean[100];
        int taskCount = 0;

        String logo = "____________________________________________________________\n";

        System.out.println(logo + " Hello! I'm Angel\n What can I do for you?\n" + logo);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(logo + " Bye. Hope to see you again soon!\n" + logo);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(logo + " Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    String status = isDone[i] ? "[X]" : "[ ]";
                    System.out.println(" " + (i + 1) + "." + status + " " + tasks[i]);
                }
                System.out.println(logo);
            } else if (userInput.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    isDone[taskIndex] = true;
                    System.out.println(logo + " Nice! I've marked this task as done:\n"
                            + "   [X] " + tasks[taskIndex] + "\n" + logo);
                }
            } else if (userInput.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    isDone[taskIndex] = false;
                    System.out.println(logo + " OK, I've marked this task as not done yet:\n"
                            + "   [ ] " + tasks[taskIndex] + "\n" + logo);
                }
            } else {
                tasks[taskCount] = userInput;
                isDone[taskCount] = false;  // Default status is not done
                taskCount++;
                System.out.println(logo + " added: " + userInput + "\n" + logo);
            }
        }

        scanner.close();
    }
}
