import java.util.Scanner;

public class Friday {
    private String name;
    private boolean isRunning;

    private Task[] tasks;

    private int taskCount;

    public Friday() {
        this.name = "Friday";
        this.isRunning = true;
        this.tasks = new Task[100];
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

    public void addTask(Task task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + task + "\n" +
                            "     Now you have " + taskCount + " tasks in the list.\n" +
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
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("    ____________________________________________________________");
    }

    public void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].markAsDone();
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as done:\n" +
                            "       " + tasks[taskIndex] + "\n" +
                            "    ____________________________________________________________"
            );
        } else {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Invalid task number.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    public void unmarkTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].unmarkAsDone();
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OK, I've marked this task as not done yet:\n" +
                            "       " + tasks[taskIndex] + "\n" +
                            "    ____________________________________________________________"
            );
        } else {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Invalid task number.\n" +
                            "    ____________________________________________________________"
            );
        }
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
            } else if (userInput.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                markTaskAsDone(taskIndex);
            } else if (userInput.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                unmarkTaskAsDone(taskIndex);
            } else if (userInput.startsWith("todo ")) {
                addTask(new Todo(userInput.substring(5)));
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                addTask(new Deadline(parts[0], parts[1]));
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                addTask(new Event(parts[0], parts[1], parts[2]));
            } else {
                System.out.println("    ____________________________________________________________\n" +
                        "     Sorry, I don't understand that command.\n" +
                        "    ____________________________________________________________");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Friday friday = new Friday();
        friday.run();
    }
}
