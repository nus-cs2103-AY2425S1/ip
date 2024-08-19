import java.util.Scanner;

public class Pikappi {
    static Scanner reader = new Scanner(System.in);
    static String command;
    static String[] tasks = new String[100];
    static int numTasks = 0;

    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Pika! I'm Pikappi!\nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");
    }

    public static void goodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Pi-kapi! See you again~\n");
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String task) {
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
        tasks[numTasks] = task;
        numTasks++;
    }
    
    public static void listTasks() {
        System.out.println("____________________________________________________________");
        if (numTasks == 0) {
            System.out.println("Pika-ka! You have no tasks!");
        }
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + ". " + tasks[i]);
            } else {
                break;
            }
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        greet();

        while (true) {
            command = reader.nextLine();
            if (command.equals("bye")) {
                goodbye();
                return;
            } else if (command.equals("list")) {
                listTasks();
            } else {
                addTask(command);
            }
        }
    }
}
