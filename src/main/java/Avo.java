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
                manager.listTasks();
            } else if (userInput.startsWith("todo")) {
                String[] inputs = userInput.split("todo ");
                manager.addTask(new ToDo(inputs[0]));
            } else if (userInput.startsWith("deadline")) {
                String[] inputs = userInput.split("deadline |/by ");
                manager.addTask(new Deadline(inputs[1], inputs[2]));
            } else if (userInput.startsWith("event")) {
                String[] inputs = userInput.split("event |/from |/to ");
                manager.addTask(new Event(inputs[1], inputs[2], inputs[3]));
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                manager.completeTask(index);
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                manager.unCompleteTask(index);
            }
        }
        endSession();
    }
}
