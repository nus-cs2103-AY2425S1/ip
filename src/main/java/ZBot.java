import java.util.Scanner;

public class ZBot {
    private static final Task[] tasks = new Task[100];
    private static int taskIndex = 0;

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listTask();
            } else {
                storeTask(input);
            }

            input = sc.nextLine();
        }

        sc.close();
        exit();
    }

    public static void greet() {
        System.out.println("Hello! I'm ZBot!");
        System.out.println("What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void storeTask(String input) {
        Task task = new Task(input);
        tasks[taskIndex] = task;
        taskIndex = (taskIndex + 1) % 100;
        System.out.println(String.format("added: %s\n", task));
    }

    public static void listTask() {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                System.out.println(String.format("%d. %s", i + 1, tasks[i]));
            }
        }
        System.out.println();
    }
}
