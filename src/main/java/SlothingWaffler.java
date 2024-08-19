import java.util.Scanner;

public class SlothingWaffler {
    public static void main(String[] args) {
        System.out.println("""
                Hello! I'm the Slothing Waffler!
                Let's stop slothing and get cracking!""");

        Task[] tasks = new Task[100];
        int tasksCount = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] split = input.split(" ", 2);

            if (split[0].strip().equals("bye")) {
                System.out.println("See you next time! Remember to get a waffle!");
                break;
            }
            if (split[0].strip().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasksCount; i++) {
                    System.out.println((i) + ".[" + tasks[i - 1].getStatusIcon() + "] " + tasks[i - 1].toString());
                }
            } else if (split[0].strip().equals("mark")) {
                int taskNumber = Integer.parseInt(split[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].toString());
            } else {
                tasks[tasksCount] = new Task(input);
                tasksCount++;
                System.out.println("added: " + input);
            }
        }
        scanner.close();
    }
}
