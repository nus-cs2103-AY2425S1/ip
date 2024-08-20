import java.util.Objects;
import java.util.Scanner;
public class Optimus {
    boolean isLive;
    Task[] storage;
    Integer numOfTasksStored;
    static String linebreak = "____________________________";

    public Optimus() {
        this.isLive = true;
        this.storage = new Task[100];
        this.numOfTasksStored = 0;
        this.greet();
    }

    public boolean getStatus() {
        return this.isLive;
    }

    private void addToStorage(String input) {
        this.storage[numOfTasksStored] = new Task(input);
        this.numOfTasksStored += 1;
    }

    private void markTask(String[] commands, boolean markComplete) {
        if (commands.length != 2) {
            System.out.println("Invalid command");
            return;
        }

        try {
            int taskNum = Integer.parseInt(commands[1]);
            if (taskNum <= 0 || taskNum > this.numOfTasksStored) {
                System.out.println("Invalid task number.");
                return;
            }

            Task task = this.storage[taskNum - 1];
            if (markComplete) {
                task.markAsComplete();
                System.out.println("Nice! I've marked this task as complete:");
            } else {
                task.markAsIncomplete();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(task);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        }
    }

    public void markTaskAsDone(String[] commands) {
        markTask(commands, true);
    }

    public void markTaskAsIncomplete(String[] commands) {
        markTask(commands, false);
    }

    public void printAllTasks() {
        for (int i = 1; i <= this.numOfTasksStored; i++){
            String out = String.format("%d. %s", i, this.storage[i-1]);
            System.out.println(out);
        }
    }

    private void greet() {
        System.out.println("Hello! I'm Optimus\nWhat can I do for you?");
        System.out.println(Optimus.linebreak);
    }

    private void leave() {
        this.isLive = false;
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Optimus.linebreak);
    }

    public static void main(String[] args) {
        Optimus optimus = new Optimus();
        Scanner scanner = new Scanner(System.in);
        
        while (optimus.getStatus()) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            String command = commands[0];

            switch (command) {
                case "bye":
                    optimus.leave();
                    break;
                case "list":
                    optimus.printAllTasks();
                    break;
                case "mark":
                    optimus.markTaskAsDone(commands);
                    break;
                case "unmark":
                    optimus.markTaskAsIncomplete(commands);
                    break;
                default:
                    optimus.addToStorage(input);
                    System.out.println("added: " + input);
                    break;
            }

        }
    }
}
