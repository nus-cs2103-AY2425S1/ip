
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BotimusPrime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name = "BotimusPrime";
        String greetingMessage =
                "____________________________________________________________\n" +
                        " Hello! I'm " + name + "\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________";
        String byeMessage =
                "____________________________________________________________\n" +
                        " Bye! Hope to see you again soon!\n" +
                        "____________________________________________________________\n";

        List<Task> taskList = new ArrayList<>();

        System.out.println(greetingMessage);

        while (true) {
            String input = sc.nextLine();
            System.out.println("\n");
            if (input.equals("bye")) {
                System.out.println(byeMessage);
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________\n" + "Here are the tasks in your list:\n");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, taskList.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else if (input.startsWith("mark")) {
                String[] numFinder = input.split(" ");
                int idx = Integer.parseInt(numFinder[1]) - 1;
                taskList.get(idx).markAsDone();
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done:\n" +
                        taskList.get(idx));

            } else if (input.startsWith("unmark")) {
                String[] numFinder = input.split(" ");
                int idx = Integer.parseInt(numFinder[1]) - 1;
                taskList.get(idx).markAsUndone();
                System.out.println("____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet:\n" +
                        taskList.get(idx));

            } else {
                taskList.add(new Task(input));
                System.out.println(
                        "____________________________________________________________\n" +
                                String.format("Alright, I've added the task \"%s\" to your list! \n", input) +
                                "____________________________________________________________\n");
            }
        }
        sc.close();
    }
}
