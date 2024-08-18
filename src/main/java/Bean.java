import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bean {
    private static final String NAME = "Bean";
    private Scanner scanner;
    private List<Task> tasks = new ArrayList<>();

    public Bean() {
        this.scanner = new Scanner(System.in);
    }

    public void startConversation() {
        greetAndAsk();
        while (true) {
            String input = scanner.nextLine().toLowerCase().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String taskNumber = parts.length > 1 ? parts[1] : "";

            if (isBye(command)) {
                break;
            } else if (command.equals("list")) {
                printList();
            } else if (command.equals("mark")) {
                markTask(Integer.parseInt(taskNumber));
            } else if (command.equals("unmark")) {
                unmarkTask(Integer.parseInt(taskNumber));
            } else {
                echo(input);
            }
        }
        exit();

    }

    private void greetAndAsk() {
        System.out.println("______________________________");
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println("______________________________");
    }

    private void exit() {
        System.out.println("______________________________");
        System.out.println("Bye. Hope to see you again.");
        System.out.println("______________________________");
        scanner.close();
    }

    private boolean isBye(String s) {
        return s.equalsIgnoreCase("bye");  // Ignoring case sensitivity for better user experience
    }

    private void echo(String s) {
        Task task = new Task(s);
        tasks.add(task);
        System.out.println("______________________________");
        System.out.println("added: "+ s);
        System.out.println("______________________________");
    }

    private void printList() {
        System.out.println("______________________________");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
        System.out.println("______________________________");
    }

    private void markTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        tasks.get(taskIndex).completeTask();

        System.out.println("______________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
        System.out.println("______________________________");
    }

    private void unmarkTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        tasks.get(taskIndex).undoTask();

        System.out.println("______________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex));
        System.out.println("______________________________");
    }

    public static void main(String[] args) {
        Bean bean = new Bean();
        bean.startConversation();
    }
}
