import java.util.ArrayList;
import java.util.Scanner;

public class Friday {
    private String name;
    private boolean isRunning;
    private ArrayList<Task> tasks;

    public Friday() {
        this.name = "Friday";
        this.isRunning = true;
        this.tasks = new ArrayList<>();
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
        tasks.add(task);
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       " + task + "\n" +
                        "     Now you have " + tasks.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________"
        );
    }

    public void listTasks() {
        System.out.println("    ____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("     Your task list is empty.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.remove(taskIndex);
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Noted. I've removed this task:\n" +
                            "       " + removedTask + "\n" +
                            "     Now you have " + tasks.size() + " tasks in the list.\n" +
                            "    ____________________________________________________________"
            );
        } else {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Invalid task number! Please enter a valid task number.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    public void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsDone();
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as done:\n" +
                            "       " + tasks.get(taskIndex) + "\n" +
                            "    ____________________________________________________________"
            );
        } else {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Invalid task number. Please enter a valid task number.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    public void unmarkTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).unmarkAsDone();
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OK, I've marked this task as not done yet:\n" +
                            "       " + tasks.get(taskIndex) + "\n" +
                            "    ____________________________________________________________"
            );
        } else {
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Invalid task number. Please enter a valid task number.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String userInput = scanner.nextLine().trim();
            Command command = Parser.parse(userInput);
            command.execute(this);
        }

        scanner.close();
    }

    public void stop() {
        isRunning = false;
        sayGoodbye();
    }

    public static void main(String[] args) {
        Friday friday = new Friday();
        friday.run();
    }
}
