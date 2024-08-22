import java.util.Scanner;
import java.util.ArrayList;

public class MonoBot {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        MonoBot.printGreeting();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String[] task = command.split(" ");
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                printTasks();
            } else if ((task[0].equals("mark")) && (task.length == 2)) {
                MonoBot.markTask(Integer.parseInt(task[1]) - 1);
            } else if ((task[0].equals("unmark")) && (task.length == 2)) {
                MonoBot.unmarkTask(Integer.parseInt(task[1]) - 1);
            } else {
                MonoBot.addTask(command);
            }
        }

        MonoBot.printFarewell();
    }

    private static void hLine() {
        System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }

    private static void printGreeting() {
        MonoBot.hLine();
        System.out.println(" Hello! I'm MonoBot");
        System.out.println(" What can I do for you?");
        MonoBot.hLine();
    }

    private static void printFarewell() {
        MonoBot.hLine();
        System.out.println(" Bye. Hope to see you again soon!");
        MonoBot.hLine();
    }

    private static void addTask(String str) {
        Task curr = new Task(str);
        MonoBot.tasks.add(curr);
        MonoBot.hLine();
        System.out.println("Added: " + str);
        MonoBot.hLine();
    }

    private static void printTasks() {
        MonoBot.hLine();
        if (MonoBot.tasks.isEmpty()) {
            System.out.println("No tasks added yet");
        } else {
            System.out.println("Here are the tasks in your list");
            for (int i = 0; i < MonoBot.tasks.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, MonoBot.tasks.get(i));
            }
        }
        MonoBot.hLine();
    }

    private static void markTask(int i) {
        Task curr = MonoBot.tasks.get(i);
        curr.markTask();
        MonoBot.hLine();
        System.out.println("Nice! I have marked this task as completed: \n" + curr);
        MonoBot.hLine();
    }

    private static void unmarkTask(int i) {
        Task curr = MonoBot.tasks.get(i);
        curr.unmarkTask();
        MonoBot.hLine();
        System.out.println("Ok! I have marked this task as incomplete: \n" + curr);
        MonoBot.hLine();
    }
}
