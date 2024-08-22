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
            try {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < task_list.size(); i++) {
                        System.out.println(String.format("%d.%s", i + 1, task_list.get(i).toString()));
                    }
                    input = scanner.nextLine();

                } else if (input.startsWith("mark ")) {
                    if (input.substring(5).isEmpty()) {
                        throw new BoombotrozException("You can't mark nothing !!");
                    }
                    int task_index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (task_index + 1 <=0 || task_index + 1 > task_list.size()) {
                        throw new BoombotrozException("You are out of range !!");
                    }
                    task_list.get(task_index).mark_task();
                    System.out.println("Nice! I've marked this as done:");
                    System.out.println(String.format("  %s", task_list.get(task_index).toString()));
                    input = scanner.nextLine();

                } else if (input.startsWith("unmark ")) {
                    if (input.substring(7).isEmpty()) {
                        throw new BoombotrozException("You can't unmark nothing !!");
                    }
                    int task_index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (task_index + 1 <=0 || task_index + 1 > task_list.size()) {
                        throw new BoombotrozException("You are out of range !!");
                    }
                    task_list.get(task_index).unmark_task();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(String.format("  %s", task_list.get(task_index).toString()));
                    input = scanner.nextLine();

                } else if (input.startsWith("delete ")) {
                    if (input.substring(7).isEmpty()) {
                        throw new BoombotrozException("You can't delete nothing !!");
                    }
                    int task_index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (task_index + 1 <=0 || task_index + 1 > task_list.size()) {
                        throw new BoombotrozException("You are out of range !!");
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(String.format("  %s", task_list.get(task_index).toString()));
                    task_list.remove(task_index);
                    System.out.println(String.format("Now you have %d tasks in the list.", task_list.size()));
                    input = scanner.nextLine();

                } else if (input.startsWith("todo ")) {
                    String toDoTask = input.substring(5);
                    if (toDoTask.isEmpty()) {
                        throw new BoombotrozException("You can't do nothing !!");
                    }
                    Task created_task = new ToDo(false, toDoTask);
                    task_list.add(created_task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("  %s", created_task.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list.", task_list.size()));
                    input = scanner.nextLine();

                } else if (input.startsWith("deadline ")) {
                    String deadlineTaskWithTime = input.substring(9);
                    if (deadlineTaskWithTime.isEmpty()) {
                        throw new BoombotrozException("You can't do nothing !!");
                    }
                    if (!deadlineTaskWithTime.contains(" /by ")) {
                        throw new BoombotrozException("You need a deadline !!");
                    }
                    String deadlineTask = deadlineTaskWithTime.split(" /by ")[0];
                    String time = deadlineTaskWithTime.split(" /by ")[1];
                    Task created_task = new Deadline(false, deadlineTask, time);
                    task_list.add(created_task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("  %s", created_task.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list.", task_list.size()));
                    input = scanner.nextLine();

                } else if (input.startsWith("event ")) {
                    String eventTaskWithTime = input.substring(6);
                    if (eventTaskWithTime.isEmpty()) {
                        throw new BoombotrozException("You can't do nothing !!");
                    }
                    if (!eventTaskWithTime.contains(" /from ") || !eventTaskWithTime.contains(" /to ")) {
                        throw new BoombotrozException("You need a start and end !!");
                    }
                    String eventTask = eventTaskWithTime.split(" /from ")[0];
                    String time_start = eventTaskWithTime.split(" /from ")[1].split(" /to ")[0];
                    String time_end = eventTaskWithTime.split(" /from ")[1].split(" /to ")[1];
                    Task created_task = new Event(false, eventTask, time_start, time_end);
                    task_list.add(created_task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("  %s", created_task.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list.", task_list.size()));
                    input = scanner.nextLine();

                } else {
                    throw new BoombotrozException("That's nonsense !!");
                }

            } catch (BoombotrozException e) {
                System.out.println(e.getMessage());
                input = scanner.nextLine();

            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

