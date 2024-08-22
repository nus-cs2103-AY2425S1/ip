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
                System.out.println("Your task list:");
                list(tasks);
            } else if (input.equals("mark")) {
                int taskIndex = scanner.nextInt();
                tasks.get(taskIndex - 1).mark();
            } else if (input.equals("unmark")) {
                int taskIndex = scanner.nextInt();
                tasks.get(taskIndex - 1).unmark();
            } else {
                String str = input + scanner.nextLine();
                String[] strToken = str.split(" /");
                Task t;
                String des;
                if (input.equals("todo")) {
                    des = strToken[0].substring("todo ".length());
                    t = new Todo(des);
                } else if (input.equals("event")) {
                    des = strToken[0].substring("event ".length());
                    String from = strToken[1].substring("from ".length());
                    String to = strToken[2].substring("to ".length());

                    t = new Event(des, from, to);
                } else { // deadline
                    des = strToken[0].substring("deadline ".length());
                    String by = strToken[1].substring("by ".length());
                    t = new Deadline(des, by);
                }

                tasks.add(t);
                System.out.print("added: ");
                System.out.println(t.toString());
                System.out.println("Now you have " + tasks.size() + " task(s).");
            }

        }
    }
    public static void greet() {
        System.out.println("Hello! I'm Socchat!");
        System.out.println("What can I do for you?");
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void list(ArrayList<Task> tasks) {
        for(int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            System.out.print((i + 1) + ": ");
            System.out.println(curr.toString());
        }
    }
}
