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
                    System.out.println("\t" + (i + 1) + ". " + task.toString());
                }
            }

            if (userInput.startsWith("mark ")) {
                int index = Integer.parseInt(userInput.substring(4).trim());
                if (index >= 1 && index <= taskList.size()) {
                    Task task = taskList.get(index - 1);
                    task.editStatus();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t " + task.toString());
                }
                else {
                    System.out.println("Invalid Index");
                }
            }

            if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(6).trim());
                if (index >= 1 && index <= taskList.size()) {
                    Task task = taskList.get(index - 1);
                    task.editStatus();
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.println("\t " + task.toString());
                }
                else {
                    System.out.println("Invalid Index");
                }
            }

            if (userInput.startsWith("todo ")) {
                Task toDo = new Todo(userInput.substring(5).trim());
                taskList.add(toDo);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + toDo.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
            if (userInput.startsWith("deadline ")) {
                String[] division = userInput.substring(9).trim().split("/by");
                Task deadline = new Deadline(division[0].trim(), division[1].trim());
                taskList.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + deadline.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
            if (userInput.startsWith("event ")) {
                String[] division = userInput.substring(6).trim().split("/from");
                String[] timeDivision = division[1].trim().split("/to");
                Task event = new Event(division[0].trim(), timeDivision[0], timeDivision[1]);
                taskList.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + event.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }

            if (!userInput.equalsIgnoreCase("bye") && !userInput.equalsIgnoreCase("list") &&
                    !userInput.startsWith("mark ") && !userInput.startsWith("unmark ") &&
                    !userInput.startsWith("todo ") && !userInput.startsWith("deadline ") &&
                    !userInput.startsWith("event ")) {
                System.out.println("\tadded: " + userInput);
                taskList.add(new Task(userInput));
            }
        }
        detector.close();
    }
}
