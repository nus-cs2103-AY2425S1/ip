import java.util.Objects;
import java.util.Scanner;
public class Rob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;
        String greet = "Hello! I'm Rob\n" +
                "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        System.out.println(greet);

        while (true) {
            String input = scanner.nextLine();

            // exit
            if (Objects.equals(input, "bye")) {
                System.out.println(exit);
                break;
            } else if (Objects.equals(input, "list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++)
                {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________\n");
            } else {
                tasks[taskCount] = input;
                taskCount++;

                // echo
                String echo = "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n";
                System.out.println(echo);
            }
        }
        scanner.close();
    }
}
