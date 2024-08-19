import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alice {
    private static final String name = "Alice";
    private static final String line =
            "____________________________________________________________";
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
         while (true) {
             String input = scanner.nextLine();
             if (input.equals("bye")) {
                 break;
             } else if (input.equals("list")) {
                 listTasks();
             } else {
                 tasks.add(input);
                 String s = "added: " + input;
                 System.out.println(line);
                 System.out.println(s);
                 System.out.println(line);
             }
         }
        exit(scanner);
    }

    public static void listTasks() {
        System.out.println(line);
        for (int i = 0; i < tasks.size(); i++) {
            String currTask = tasks.get(i);
            int currNum = i + 1;
            String s = currNum + ". " + currTask;
            System.out.println(s);
        }
        System.out.println(line);
    }

    private static void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    private static void exit(Scanner scanner) {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        scanner.close();
    }
}
