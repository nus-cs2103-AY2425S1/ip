import java.util.ArrayList;
import java.util.Scanner;

public class Kafka {

    public ArrayList<Task> tasks = new ArrayList<>();

    public void greet() {
        String message = """
              Hello. Kafka here.
              I have been waiting for your arrival.
            """;
        System.out.println(message);
    }

    public void goodbye() {
        String message = "  Farewell. I look forward to our next meeting, wherever destiny may lead us. ";
        System.out.println(message);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("  added: " + task.description);
    }

    public void createList() {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            String listMessage = "  " + (i + 1) + ".[" + t.getStatusIcon() + "] " + t.description;
            System.out.println(listMessage);
        }
    }

    public void mark(int taskNumber) {
        Task t = this.tasks.get(taskNumber - 1);
        t.markAsDone();
        String message = "  Nice! I've marked this task as done:\n"
                + "    [" + t.getStatusIcon() + "] "
                + t.description;
        System.out.println(message);
    }

    public void unmark(int taskNumber) {
        Task t = this.tasks.get(taskNumber - 1);
        t.markAsNotDone();
        String message = "  OK, I've marked this task as not done yet:\n"
                + "    [" + t.getStatusIcon() + "] "
                + t.description;
        System.out.println(message);
    }

    public static void main(String[] args) {
        String logo = """
                   __  __            __     _
                  |  |/  /  ____   _/  /_  | |      ____
                  |     /  / _  | |_    _| | |/ /  / _  |
                  |     \\ | |_| |   |  |   |   <  | |_| |
                  |__|\\__\\ \\____|   |__|   |_|\\ \\  \\____|
                """;
        Scanner scanner = new Scanner(System.in);
        Kafka kafka = new Kafka();
        boolean exitChat = false;

        System.out.println("  Hello from\n" + logo);
        kafka.greet();
        System.out.println("  What do you need me for?");
        while (!exitChat) {
            String[] userInput = scanner.nextLine().trim().split(" ", 2);
            if (userInput[0].equalsIgnoreCase("bye")) {
                exitChat = true;
            } else if (userInput[0].equalsIgnoreCase("list")) {
                kafka.createList();
            } else if (userInput[0].equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(userInput[1]);
                kafka.mark(taskNumber);
            } else if (userInput[0].equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(userInput[1]);
                kafka.unmark(taskNumber);
            } else {
                String description = String.join(" ", userInput);
                Task task = new Task(description);
                kafka.addTask(task);
            }
        }
        kafka.goodbye();
    }
}
