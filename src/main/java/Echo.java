import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Echo {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        String logo = "____________________________________________________________\n";
        System.out.println("Hello, I'm Echo\n" + logo);
        String input = null;

        while (true) {
            input = myObj.nextLine().trim().toLowerCase();
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            switch (command) {
                case "bye":
                    exitProgram();
                    return;
                case "list":
                    listTasks(tasks);
                    break;
                case "mark":
                    if (parts.length == 2) {
                        markTask(tasks, parts[1]);
                    } else {
                        System.out.println("Please specify the task number to mark.");
                    }
                    break;
                case "unmark":
                    if (parts.length == 2) {
                        unmarkTask(tasks, parts[1]);
                    } else {
                        System.out.println("Please specify the task number to unmark.");
                    }
                    break;
                default:
                    addTask(tasks, input);
                    break;
            }
        }
    }

    private static void exitProgram() {
        System.out.println("Bye bye...");
    }

    private static void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
        } else {
            int count = 1;
            for (Task task : tasks) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    private static void markTask(List<Task> tasks, String input) {
        int num = Integer.parseInt(input);
        if (num > 0 && num <= tasks.size()) {
            Task doneTask = tasks.get(num - 1);
            doneTask.setDone();
            System.out.println("Nice! I've marked this task as done:\n" + doneTask);
        }
    }

    private static void unmarkTask(List<Task> tasks, String input) {
        int num = Integer.parseInt(input);
        if (num > 0 && num <= tasks.size()) {
            Task undoneTask = tasks.get(num - 1);
            undoneTask.setUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + undoneTask);
        }
    }

    private static void addTask(List<Task> tasks, String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);
        System.out.println("added: " + taskDescription);
    }
}


