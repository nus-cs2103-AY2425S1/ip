import java.util.Scanner;

public class Taskalyn {
    public static void main(String[] args) {

        // Initialising task manager
        TaskManager taskManager = new TaskManager();

        // Initialising scanner
        Scanner scanner = new Scanner(System.in);

        // Greeting user
        taskManager.printLines("Hey! I'm Taskalyn, your personal Task Manager :)\n" +
                "    What can I do for you?");

        // Conditions
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                taskManager.printLines("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                taskManager.listTasks();
            } else {
                taskManager.addTask(input);
                taskManager.printLines(input);
            }
        }

    }
}
