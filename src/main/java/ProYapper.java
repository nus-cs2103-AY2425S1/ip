import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ProYapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String greeting = "Hello! I am Pro Yapper!\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";

        List<Task> taskList = new ArrayList<>();

        System.out.println(greeting);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("\n" + goodbye);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("\nHere are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    int lstNum = i + 1;
                    Task next = taskList.get(i);
                    String name = next.getDescription();
                    String icon = next.getStatusIcon();
                    System.out.println(lstNum + ".[" + icon + "] " + name);
                }
                System.out.println("\n");
            } else if (userInput.startsWith("mark")) {
                System.out.println("\nNice! I've marked this task as done:");
                String[] parts = userInput.split(" ");
                int lstNum = Integer.parseInt(parts[1]);
                Task marked = taskList.get(lstNum - 1);
                marked.markAsDone();
                String name = marked.getDescription();
                String icon = marked.getStatusIcon();
                System.out.println("[" + icon + "] " + name + "\n");
            } else if (userInput.startsWith("unmark")) {
                System.out.println("\nOK, I've marked this task as not done yet:");
                String[] parts = userInput.split(" ");
                int lstNum = Integer.parseInt(parts[1]);
                Task marked = taskList.get(lstNum - 1);
                marked.markAsUndone();
                String name = marked.getDescription();
                String icon = marked.getStatusIcon();
                System.out.println("[" + icon + "] " + name + "\n");
            } else {
                taskList.add(new Task(userInput));
                System.out.println("\nadded: " + userInput + "\n");
            }
        }
        scanner.close();
    }
}

