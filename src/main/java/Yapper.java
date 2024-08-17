import java.util.Scanner;

public class Yapper {

    private static String divider = "____________________________________________________________";
    private static String name = "Yapper";

    public static void main(String[] args) {

        System.out.println(divider);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(divider);

        Task[] taskList = new Task[100];
        int totalTasks = 0;

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] split = input.split(" ");
            String command = split[0];

            if (command.equals("bye")) {
                System.out.println(divider);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(divider);
                break;
            } else if (command.equals("list")) {
                System.out.println(divider);
                for (int i = 1; i <= totalTasks; i++) {
                    System.out.println(i + "." + taskList[i - 1]);
                }
                System.out.println(divider);
            } else if (command.equals("mark") | command.equals("unmark")) {
                int taskNumber = Integer.parseInt(split[1]);
                if (taskNumber < 1 || taskNumber > totalTasks) {
                    System.out.println(divider);
                    System.out.println("Oopsie!, Couldn't find that one! :)");
                    System.out.println(divider);
                    continue;
                }

                String message = "";
                if (command.equals("mark")) {
                    message = "Nice! I've marked this task as done:";
                    taskList[taskNumber - 1].mark();
                } else {
                    message = "OK, I've marked this task as not done yet:";
                    taskList[taskNumber - 1].unmark();
                }
                System.out.println(divider);
                System.out.println(message);
                System.out.println(" " + taskList[taskNumber - 1]);
                System.out.println(divider);
            } else if (command.equals("todo")) {
                Task task = new ToDo(join(split, 1, split.length));
                addTask(taskList, totalTasks, task);
                totalTasks++;
            } else if (command.equals("deadline")) {
                int byIndex = 0;
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals("/by")) {
                        byIndex = i;
                    }
                }
                Task task = new Deadline(join(split, 1, byIndex), join(split, byIndex + 1, split.length));
                addTask(taskList, totalTasks, task);
                totalTasks++;
            } else if (command.equals("event")) {
                int fromIndex = 0;
                int toIndex = 0;
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals("/from")) {
                        fromIndex = i;
                    } else if (split[i].equals("/to")) {
                        toIndex = i;
                    }
                }
                Task task = new Event(join(split, 1, fromIndex),
                                      join(split, fromIndex + 1, toIndex),
                                      join(split, toIndex + 1, split.length));
                addTask(taskList, totalTasks, task);
                totalTasks++;
            } else {
                System.out.println(divider);
                System.out.println(input);
                System.out.println(divider);
            }
        }
    }

    public static void addTask(Task[] taskList, int totalTasks, Task task) {
        taskList[totalTasks++] = task;
        System.out.println(divider);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " " + taskPlural(totalTasks) + " in the list.");
        System.out.println(divider);
    }

    public static String taskPlural(int taskCount) {
        String taskMessage = "task";
        if (taskCount != 1) {
            taskMessage += "s";
        }
        return taskMessage;
    }

    public static String join(String[] s, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(s[i]);
            if (i != end - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
