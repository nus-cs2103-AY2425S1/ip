import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Socchat {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            String input = scanner.next();
            System.out.print("> ");
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                list(tasks);
            } else if (input.equals("mark")) {
                int taskIndex = scanner.nextInt();
                tasks.get(taskIndex - 1).mark();
            } else if (input.equals("unmark")) {
                int taskIndex = scanner.nextInt();
                tasks.get(taskIndex - 1).unmark();
            } else {
                String str = input + scanner.nextLine();
                Task task = new Task(str);
                tasks.add(task);
                System.out.print("added: ");
                System.out.println(str);
            }

        }
    }
    public static void greet() {
        System.out.println("Hello! I'm Socchat!");
        System.out.println("What can I do for you?");
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon! ");
    }
    public static void list(ArrayList<Task> tasks) {
        for(int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            System.out.print((i + 1) + ": ");
            System.out.print(curr.getStatusIcon() + " ");
            System.out.println(tasks.get(i).getDescription());
        }
    }
}
