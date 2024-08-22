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

            } else if (input.startsWith("todo ")) {
                String toDoTask = input.substring(5);
                Task created_task = new ToDo(false, toDoTask);
                task_list.add(created_task);
                System.out.println("Got it. I've added this task:");
                System.out.println(created_task.toString());
                System.out.println(String.format("Now you have %d tasks in the list.", task_list.size()));
                input = scanner.nextLine();

            } else if (input.startsWith("deadline ")) {
                String deadlineTask = input.substring(9).split(" /by ")[0];
                String time = input.substring(9).split(" /by ")[1];
                Task created_task = new Deadline(false, deadlineTask, time);
                task_list.add(created_task);
                System.out.println("Got it. I've added this task:");
                System.out.println(created_task.toString());
                System.out.println(String.format("Now you have %d tasks in the list.", task_list.size()));
                input = scanner.nextLine();

            } else if (input.startsWith("event ")) {
                String eventTask = input.substring(6).split(" /from ")[0];
                String time_start = input.substring(6).split(" /from ")[1].split(" /to ")[0];
                String time_end = input.substring(6).split(" /from ")[1].split(" /to ")[1];
                Task created_task = new Event(false, eventTask, time_start, time_end);
                task_list.add(created_task);
                System.out.println("Got it. I've added this task:");
                System.out.println(created_task.toString());
                System.out.println(String.format("Now you have %d tasks in the list.", task_list.size()));
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

