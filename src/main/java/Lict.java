import java.util.ArrayList;
import java.util.Scanner;

public class Lict {
    private final static String name = "Lict";
    private final static String horizontal_line = "__________________________________";
    private final static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontal_line);
        System.out.println("Hello! I'm "+ name);
        System.out.println("What can I do for you?");
        System.out.println(horizontal_line);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(horizontal_line);
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int counter = 1;
                for (Task task : tasks) {
                    String info = String.format("%d.[%s] ", counter, task.getStatusIcon());
                    System.out.println(info + task);
                    counter+=1;
                }
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(4).trim()) - 1;
                Task t = tasks.get(index);
                t.changeStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("  [%s] %s", t.getStatusIcon(), t.description));
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(6).trim()) - 1;
                Task t = tasks.get(index);
                t.changeStatus(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format("  [%s] %s", t.getStatusIcon(), t.description));
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
            System.out.println(horizontal_line);
            input = sc.nextLine();
        }
        sc.close();
        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_line);
    }
}
