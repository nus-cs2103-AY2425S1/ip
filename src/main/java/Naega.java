import java.util.Scanner;
import java.util.ArrayList;

public class Naega {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Naega");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String userInput;

        // Echo loop until user types "bye"
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(taskIndex));
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("unmark ")) {
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(taskIndex));
                    System.out.println("____________________________________________________________");
                } else {
                    Task newTask = new Task(userInput);
                    tasks.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println(" added: " + userInput);
                    System.out.println("____________________________________________________________");
                }
            }
            scanner.close();
        }
}
