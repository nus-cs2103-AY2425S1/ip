import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Avo {
    private static void greet() {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    private static void endSession() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    public static void main(String[] args) {
        greet();
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "exit")) {
                break;
            }
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                manager.listTasks();
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                manager.completeTask(index);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(index + 1 + ". " + manager.getItem(index));
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                manager.completeTask(index);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(index + 1 + ". " + manager.getItem(index));
            } else {
                manager.addTask(userInput);
                System.out.println("added: " + userInput);
            }
        }
        endSession();
    }
}
