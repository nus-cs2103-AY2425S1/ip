import java.util.ArrayList;
import java.util.Scanner;

public class Charlotte {
    public static void main(String[] args) {
        String line = "_________________________________________________";
        System.out.println(line + "\n Hello! I'm Charlotte!\n What can I do for you?\n" + line);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String input;


        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                System.out.println(line + "\n Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
                }
                System.out.println(line);
            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.get(taskNumber).markAsDone();
                    System.out.println(line + "\n Nice! I've marked this task as done:\n  "
                            + tasks.get(taskNumber) + "\n" + line);
                }
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.get(taskNumber).unmark();
                    System.out.println(line + "\n OK, I've marked this task as not done yet:\n  "
                            + tasks.get(taskNumber) + "\n" + line);
                }
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(line + "\n added: " + input + "\n" + line);
            }

        }

        System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);

        scanner.close();
    }
}
