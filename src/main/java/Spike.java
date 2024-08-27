import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Spike {

    public static void helloMessage() {
        System.out.println("_________________________________________________________");
        System.out.println("Hello! I'm Spike\nWhat can I do for you?");
        System.out.println("_________________________________________________________");
        return;
    }

    public static void byeMessage() {
        System.out.println("     _________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("     _________________________________________________________");
        return;
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<>(100);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                byeMessage();
                break;
            } else if (input.startsWith("mark")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                toDoList.get(index).markAsDone();
            } else if (input.startsWith("unmark")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                toDoList.get(index).unmark();
            } else if (input.equals("list")) {
                System.out.println("     _________________________________________________________");
                System.out.println("      Here are the tasks in your list:");
                int counter = 0;
                for (Task item : toDoList) {
                    counter++;
                    System.out.println("      " + counter + ". " + item.toString());
                }
                System.out.println("     _________________________________________________________");
            } else {
                toDoList.add(addTask(input));
            }
        }
        scanner.close();
    }

    public static Task addTask(String input) {
        Task task = new Task(input);
        System.out.println("     _________________________________________________________");
        System.out.println("     " + "added: " + input);
        System.out.println("     _________________________________________________________");
        return task;
    }

    public static void main(String[] args) {
        helloMessage();
        echo();
    }
}
