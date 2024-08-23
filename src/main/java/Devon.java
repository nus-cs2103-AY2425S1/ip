import java.util.Scanner;

public class Devon {

    private Scanner scanner = new Scanner(System.in);
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    private void printLongLine() {
        String LINE_SEPARATOR = "____________________";
        System.out.println("\t" + LINE_SEPARATOR);
    }

    private void introduction() {
        this.printLongLine();
        System.out.println("\t" + "Hello! I'm Devon.");
        System.out.println("\t" + "What can I do for you?");
        this.printLongLine();
    }

    private void goodbye() {
        this.printLongLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        this.printLongLine();
    }

    private void receiveUserInput() {
        String input = this.scanner.nextLine();
        if (input.equals("bye")) {
            this.goodbye();
        } else if (input.equals("list")) {
            this.printList();
            this.receiveUserInput();
        } else {
            this.addToList(input);
            this.receiveUserInput();
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task current = tasks[i];
            String formattedEntry = String.format(
                    "\t" + "%d. [%s] %s",
                    i + 1,
                    current.getStatusIcon(),
                    current.getDescription()
            );
            System.out.println(formattedEntry);
        }
        this.printLongLine();
    }

    public static void main(String[] args) {
        Devon bot = new Devon();
        bot.introduction();
        bot.receiveUserInput();
    }
}
