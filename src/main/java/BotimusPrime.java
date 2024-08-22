
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

            } else if (input.startsWith("todo")) {
                ToDo task = new ToDo(input);

                taskList.add(task);

                System.out.println(
                        "____________________________________________________________\n" +
                                String.format("Alright, I've added the task:\n %s \nNow you have %d tasks in the list.\n", task, taskList.size()) +

                                "____________________________________________________________\n");
            } else if (input.startsWith("deadline")) {
                String[] parser = input.split("/by ");

                String description = parser[0].substring(9);
                String deadline = parser[1];

                Deadline task = new Deadline(description, deadline);

                taskList.add(task);

                System.out.println(
                        "____________________________________________________________\n" +
                                String.format("Alright, I've added the task:\n %s \nNow you have %d tasks in the list.\n", task, taskList.size()) +

                                "____________________________________________________________\n");
            } else if (input.startsWith("event")) {
                String[] parser = input.split("/from ");

                String description = parser[0].substring(6);
                String times = parser[1];

                String[] fromAndTo = times.split("/to ");

                String from = fromAndTo[0];
                String to = fromAndTo[1];

                Event task = new Event(description, from, to);

                taskList.add(task);

                System.out.println(
                        "____________________________________________________________\n" +
                                String.format("Alright, I've added the task:\n %s \nNow you have %d tasks in the list.\n", task, taskList.size()) +

                                "____________________________________________________________\n");
            } else {
                System.out.println("Invalid command, try again");
            }
        }
        sc.close();
    }
}
