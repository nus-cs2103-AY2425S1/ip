import java.util.Scanner;

public class Calebyyy {
    private String[] tasks;
    private int taskCount;
    private Scanner scanner;

    public Calebyyy() {
        tasks = new String[100];
        taskCount = 0;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Calebyyy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else {
                addTask(input);
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close();
    }

    private void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + task);
        System.out.println("____________________________________________________________");
    }

    private void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Calebyyy chatbot = new Calebyyy();
        chatbot.start();
    }
}