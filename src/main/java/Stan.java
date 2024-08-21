import java.util.Scanner;
public class Stan {
    public static void main(String[] args) {
        String logo = "Stan";
        String[] tasks = new String[100];
        int taskCount = 0;
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + logo);
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();

    }
}
