import java.util.Scanner;

public class Friday {
    private String name;
    private boolean isRunning;

    private String[] tasks;

    private int taskCount;

    public Friday() {
        this.name = "Friday";
        this.isRunning = true;
        this.tasks = new String[100];
        this.taskCount = 0;
    }

    public void greet() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hello! I'm " + name + "\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________"
        );
    }

    public void echo(String userInput) {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     " + userInput + "\n" +
                        "    ____________________________________________________________"
        );
    }

    public void sayGoodbye() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________"
        );
    }

    public void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     added: " + task + "\n" +
                            "    ____________________________________________________________"
            );
        } else {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Sorry, I can't store more tasks!\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    public void listTasks() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("    ____________________________________________________________");
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                sayGoodbye();
                isRunning = false;
            } else if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Friday friday = new Friday();
        friday.run();
    }
}
