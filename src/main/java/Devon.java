import java.util.Scanner;

public class Devon {

    private Scanner scanner = new Scanner(System.in);
    private String[] tasks = new String[100];
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

    private void addToList(String task) {
        this.tasks[taskCount] = task;
        taskCount++;
        this.printLongLine();
        System.out.println("\t" + "added: " + task);
        this.printLongLine();
    }

    private void printList() {
        this.printLongLine();
        for (int i = 0; i < taskCount; i++) {
            String formattedEntry = String.format("\t" + "%d. %s", i + 1, tasks[i]);
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
