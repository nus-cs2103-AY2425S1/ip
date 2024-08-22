import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Gary {
    public static void main(String[] args) {
        Scanner detector = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        String greeting = """
                ────────────────────────────────────────────
                 Hello! I'm Gary
                 What can I do for you?     
                ──────────────────────────────────────────── 
                """;
        System.out.println(greeting);

        while (true) {
            String userInput = detector.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    System.out.println("\t" + (i + 1) + ". [" + task.getStatusIcon() + "] " +
                                       task.getTaskDescription());
                }
            }

            if (userInput.startsWith("mark ")) {
                int index = Integer.parseInt(userInput.substring(4).trim());
                if (index >= 1 && index <= taskList.size()) {
                    Task task = taskList.get(index - 1);
                    task.isDone = true;
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t " + "[" + task.getStatusIcon() + "] " + task.getTaskDescription());
                }
                else {
                    System.out.println("Invalid Index");
                }
            }

            if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(6).trim());
                if (index >= 1 && index <= taskList.size()) {
                    Task task = taskList.get(index - 1);
                    task.isDone = false;
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.println("\t " + "[" + task.getStatusIcon() + "] " + task.getTaskDescription());
                }
                else {
                    System.out.println("Invalid Index");
                }
            }

            if (!userInput.equalsIgnoreCase("bye") && !userInput.equalsIgnoreCase("list") &&
                    !userInput.startsWith("mark ") && !userInput.startsWith("unmark ")) {
                System.out.println("\tadded: " + userInput);
                taskList.add(new Task(userInput));
            }
        }
        detector.close();
    }
}
