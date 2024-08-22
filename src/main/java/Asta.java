import java.util.Scanner;

public class Asta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;
        boolean[] isDone = new boolean[100];

        System.out.println("Hello! I'm Asta\n" + "What can I do for you?\n");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    String status = isDone[i] ? "[X] " : "[ ] ";
                    System.out.println((i + 1) + ". " + status + tasks[i]);
                }
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    isDone[taskNumber] = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + tasks[taskNumber]);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    isDone[taskNumber] = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + tasks[taskNumber]);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                tasks[taskCount] = input;
                taskCount++;
                isDone[taskCount] = false;
                System.out.println("added: " + input);
            }
        }

        scanner.close();
    }
}
