import java.util.Scanner;
import java.util.ArrayList;

public class MonoBot {

    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        MonoBot.printGreeting();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                printTasks();
            } else {
                addTask(command);
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
        MonoBot.tasks.add(str);
        MonoBot.hLine();
        System.out.println("Added: " + str);
        MonoBot.hLine();
    }

    private static void printTasks() {
        MonoBot.hLine();
        if (MonoBot.tasks.isEmpty()) {
            System.out.println("No tasks added yet");
        } else {
            for (int i = 0; i < MonoBot.tasks.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, MonoBot.tasks.get(i));
            }
        }
        MonoBot.hLine();
    }
}
