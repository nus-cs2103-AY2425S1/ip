import java.util.Scanner;

public class Calebyyy {
    private String[] tasks;
    private int taskCount;
    private Scanner scanner;
    private CommandManager commandManager;

    public Calebyyy() {
        tasks = new String[100];
        taskCount = 0;
        scanner = new Scanner(System.in);
        commandManager = new CommandManager(this);
    }

    public void start() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Calebyyy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            commandManager.executeCommand(input);

        }
    }
    

    public void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new Calebyyy().start();
    }
}