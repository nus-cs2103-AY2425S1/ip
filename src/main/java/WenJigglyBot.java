import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WenJigglyBot {
    static List<Task> tasks = new ArrayList<>(100);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "WenJigglyBot";
        System.out.println("Sup im " + name);
        System.out.println("What can I do for you?");
        String task = "";
        while (!task.equals("bye")) {
                task = scanner.nextLine();
            if (task.equals("list")) {
                displayTasks();
            } else if (task.contains("mark") || task.contains("unmark")) {
                String[] strings = task.split(" ");
                String action = strings[0];
                int idx = Integer.parseInt(strings[1]) - 1;
                toggleTask(action, idx);
            } else if (task.contains("todo")) {
                String taskName = task.replaceFirst("todo", "").trim();
                addTask(new ToDoTask(taskName));
            } else if (task.contains("deadline")) {
                // remove deadline tag
                String taskNameAndDeadline = task.replaceFirst("deadline", "").trim();

                // split the title and deadline
                String[] parts = taskNameAndDeadline.split("/by");
                String taskName = parts[0].trim();
                String deadline = parts[1].trim();

                addTask(new DeadlineTask(taskName, deadline));

            } else if (task.contains("event")) {
                // Split the string by "/from" and "/to"
                String[] fromParts = task.split("/from");
                String[] toParts = fromParts[1].split("/to");

                // Extract the event description, start time, and end time
                String event = fromParts[0].replaceFirst("event", "").trim();
                String startTime = toParts[0].trim();
                String endTime = toParts[1].trim();

                addTask(new EventTask(event, startTime, endTime));

            } else if (task.equals("bye")) {
                break;
            } else {
                addTask(new Task(task));
            }
        }
        System.out.println("Goodbye!");
    }

    private static void toggleTask(String action, int idx) {
        Task task = tasks.get(idx);
        if (action.equals("mark")) {
            System.out.println("____________________________________________________________");
            System.out.println("\tYay! Task Completed!");
            task.markTask();
            System.out.println("\t" + task);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("\tGet to work boy, why not done!!!");
            tasks.get(idx).unmarkTask();
            System.out.println("\t" + task);
            System.out.println("____________________________________________________________");
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.printf("\tAdding %s\n", task.taskType());
        System.out.printf("\tDone! Added: %s\n", task.getDescription());
        System.out.printf("You now have %d tasks!\n", tasks.size());
        System.out.println("____________________________________________________________");
    }

    private static void displayTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are your tasks :)");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s%n", i+1, tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }
}
