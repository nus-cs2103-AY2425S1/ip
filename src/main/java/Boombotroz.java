import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Boombotroz {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Boombotroz\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Task> task_list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < task_list.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, task_list.get(i).toString()));
                }
                input = scanner.nextLine();

            } else if (input.startsWith("mark ")) {
                int task_nb = Integer.parseInt(input.split(" ")[1]) - 1;
                task_list.get(task_nb).mark_task();
                System.out.println("Nice! I've marked this as done:");
                System.out.println(task_list.get(task_nb).toString());
                input = scanner.nextLine();

            } else if (input.startsWith("unmark ")) {
                int task_nb = Integer.parseInt(input.split(" ")[1]) - 1;
                task_list.get(task_nb).unmark_task();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task_list.get(task_nb).toString());
                input = scanner.nextLine();

            } else {
                task_list.add(new Task(false, input));
                System.out.println(String.format("added: %s", input));
                input =  scanner.nextLine();

            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

