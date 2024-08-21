import java.util.Scanner;

public class Jay {
    public static void main(String[] args) {
        Jay jay = new Jay("Jay");
        jay.start();
    }

    private final String name;
    private String[] tasks;
    private int taskCount;

    public Jay(String name) {
        this.name = name;
        this.tasks = new String[100];
        this.taskCount = 0;
    }

    public void start() {
        System.out.println(this.greet());
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(this.quit());
                break;
            } else {
                System.out.println(this.processCommand(command));
            }
        }
    }

    private String greet() {
        String greet =
                " Hello! I'm " + this.name + "\n" +
                " What can I do for you?";
        return formatCommand(greet);
    }

    private String quit() {
        return formatCommand("Bye. Hope to see you again soon!");
    }

    private String processCommand(String command) {
        if (command.equals("list")) {
            return this.showTasks();
        } else {
            return this.addTask(command);
        }
    }

    private String showTasks() {
        StringBuilder tasks = new StringBuilder();

        for (int i = 0; i < this.taskCount; i++) {
            if (i == this.taskCount - 1) {
                tasks.append((i + 1)).append(". ").append(this.tasks[i]);
            } else {
                tasks.append((i + 1)).append(". ").append(this.tasks[i]).append("\n");
            }
        }

        return formatCommand(tasks.toString());
    }

    private String addTask(String task) {
        this.tasks[this.taskCount] = task;
        this.taskCount++;
        return formatCommand("added: " + task);
    }

    private String formatCommand(String command) {
        return "____________________________________________________________\n" +
                command + "\n" +
                "____________________________________________________________\n";
    }

}
