import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bean {
    private static final String NAME = "Bean";
    private Scanner scanner;
    private List<Task> tasks = new ArrayList<>();
    private int numOfTasks;

    public Bean() {
        this.scanner = new Scanner(System.in);
    }

    public void startConversation() {
        greetAndAsk();
        while (true) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String details = parts.length > 1 ? parts[1] : "";

            if (isBye(command)) {
                break;
            } else if (command.equals("list")) {
                printList();
            } else if (command.equals("mark")) {
                markTask(Integer.parseInt(details));
            } else if (command.equals("unmark")) {
                unmarkTask(Integer.parseInt(details));
            } else if (command.equals("todo")) {
                TodoTask tt = new TodoTask(details);
                tasks.add(tt);
                numOfTasks++;
                echo(tt);
            } else if (command.equals("deadline")){
                String[] deadlineParts = details.split(" /by ");
                DeadlineTask dt = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
                tasks.add(dt);
                numOfTasks++;
                echo(dt);
            } else if (command.equals("event")){
                String[] eventParts = details.split(" /from | /to ");
                EventTask et = new EventTask(eventParts[0], eventParts[1], eventParts[2]);
                tasks.add(et);
                numOfTasks++;
                echo(et);
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

    private void echo(Task task) {
        System.out.println("______________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("______________________________");
    }

    private void printList() {
        System.out.println("______________________________");
        System.out.println("Here are the tasks in your list");
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