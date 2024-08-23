import java.util.Scanner;

public class Devon {

    protected Scanner scanner = new Scanner(System.in);
    protected Task[] tasks = new Task[100];
    protected int taskCount = 0;

    private void printLongLine() {
        String LINE_SEPARATOR = "____________________";
        System.out.println("\t" + LINE_SEPARATOR);
    }

    private void introduction() {
        printLongLine();
        System.out.println("\t" + "Hello! I'm Devon.");
        System.out.println("\t" + "What can I do for you?");
        printLongLine();
    }

    private void goodbye() {
        printLongLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLongLine();
    }

    private String detectCommand(String msg) {
        String[] parts = msg.split(" ");
        return parts[0];
    }

    private String detectContent(String msg) {
        String[] parts = msg.split(" ");
        return parts.length > 1 ? parts[1] : "Error";
    }

    private void receiveUserInput() {
        String input = scanner.nextLine();
        String command = detectCommand(input);
        if (command.equals("bye")) {
            goodbye();
        } else if (command.equals("list")) {
            printList();
            receiveUserInput();
        } else if (command.equals("mark")) {
            int taskIndex = Integer.parseInt(detectContent(input)) - 1;
            markAsDone(tasks[taskIndex]);
            receiveUserInput();
        } else if (command.equals("unmark")) {
            int taskIndex = Integer.parseInt(detectContent(input)) - 1;
            markAsUndone(tasks[taskIndex]);
            receiveUserInput();
        } else {
            addToList(input);
            receiveUserInput();
        }
    }

    private void addToList(String taskDescription) {
        this.tasks[taskCount] = new Task(taskDescription);
        taskCount++;
        this.printLongLine();
        System.out.println("\t" + "added: " + taskDescription);
        this.printLongLine();
    }

    private void printList() {
        this.printLongLine();
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task current = tasks[i];
            String formattedEntry = String.format(
                    "\t" + "%d. %s",
                    i + 1,
                    current
            );
            System.out.println(formattedEntry);
        }
        this.printLongLine();
    }

    void markAsDone(Task task) {
        printLongLine();
        task.markAsDone();
        printLongLine();
    }

    void markAsUndone(Task task) {
        printLongLine();
        task.markAsUndone();
        printLongLine();
    }

    public static void main(String[] args) {
        Devon bot = new Devon();
        bot.introduction();
        bot.receiveUserInput();
    }
}
