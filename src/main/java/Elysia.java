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
                System.out.println(line);
                System.out.println(exitMessage);
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark ")) {
                markAsDone(input);
            } else if (input.startsWith("unmark ")) {
                unmarkAsDone(input);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                System.out.println(line);
                System.out.println("Got it. I've added this task:");

                if (input.startsWith("todo")) {
                    Task task = addToDos(input.substring(5));
                    System.out.println(task);
                } else if (input.startsWith("deadline")) {
                    Task task = addDeadline(input.substring(9));
                    System.out.println(task);
                } else {
                    Task task = addEvent(input.substring(6));
                    System.out.println(task);
                }
                System.out.println("Now you have " + arrayList.size() + " tasks in the list.");
                System.out.println(line);
            }
        }
    }

    public static Task addToDos(String s) {
        Task task = new ToDos(s);
        arrayList.add(task);
        return task;
    }

    //deadline return book /by Sunday
    public static Task addDeadline(String s) {
        String[] str = s.split("/by ");
        Task task = new Deadline(str[0], str[1]);
        arrayList.add(task);
        return task;
    }

    public static Task addEvent(String s) {
        String[] str = s.split("/from | /to ");
        Task task = new Event(str[0], str[1], str[2]);
        arrayList.add(task);
        return task;
    }

    public static void printList() {
        int n = arrayList.size();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= n; i++) {
            Task curr = arrayList.get(i - 1);
            System.out.println(i + "." + curr.toString());
        }
    }

    public static void markAsDone(String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(5))) - 1;
        Task curr = arrayList.get(index);
        curr.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(curr);
    }

    public static void unmarkAsDone(String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
        Task curr = arrayList.get(index);
        curr.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(curr);
    }
}
