import java.util.ArrayList;
import java.util.Scanner;

public class Elysia {
    static String line = "____________________________________________________________";
    static String welcomeMessage = "Hello! I'm Elysia\nWhat can I do for you?";
    static String exitMessage = "Bye. Hope to see you again soon!";
    private static ArrayList<Task> arrayList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        arrayList = new ArrayList<>();

        System.out.println(line);
        System.out.println(welcomeMessage);
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                int n = arrayList.size();
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i <= n; i++) {
                    Task curr = arrayList.get(i - 1);
                    System.out.println(i + "." + curr.toString());
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(String.valueOf(input.charAt(5))) - 1;
                Task curr = arrayList.get(index);
                curr.markAsDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(curr.toString());
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
                Task curr = arrayList.get(index);
                curr.unmarkAsDone();
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(curr.toString());
            } else {
            arrayList.add(new Task(input));

            System.out.println(line);
            System.out.println("added: " + input);
            System.out.println(line);
            }
        }
        System.out.println(line);
        System.out.println(exitMessage);
        System.out.println(line);
    }
}
