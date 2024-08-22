import java.util.ArrayList;
import java.util.Scanner;

public class Genji {
    private static String NAME = "Genji";
    private static String LINE = "________________________________________________________________";
    private static Scanner scanner = new Scanner(System.in);
    private static boolean flag = true;
    private static ArrayList<Task> list = new ArrayList();

    public static void addList(Task t) {
        list.add(t);
        System.out.println("added: " + t);
        System.out.println(LINE);
    }

    public static void showList() {
        int index = 1;
        for(Task task : list) {
            System.out.println(String.format("%d. ", index)+ task);
            index++;
        }
        System.out.println(LINE);
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void echo(String echo) {
        System.out.println(echo);
        System.out.println(LINE);
    }

    public static void run() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?");
        System.out.println(LINE);
        while (flag) {
            String input = scanner.nextLine();
            System.out.println(LINE);
            if (input.equals("bye")) {
                flag = false;
            } else if (input.equals("list")) {
                showList();
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                list.get(index).mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(index).toString());
                System.out.println(LINE);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                list.get(index).unmark();
                System.out.println("OK! I've marked this task as not done yet:");
                System.out.println(list.get(index).toString());
                System.out.println(LINE);
            } else if (input.startsWith("todo")) {
                ToDo td = new ToDo(input.substring(5));
                addList(td);
                System.out.println("Got it. I've added this task:");
                System.out.println(td);
                System.out.println("Now you have " + list.size() + " tasks in the list");
                System.out.println(LINE);
            } else if (input.startsWith("deadline")) {
                String name = input.substring(9, input.lastIndexOf("/"));
                String time = input.substring(input.lastIndexOf("/") + 4);
                Deadline ddl = new Deadline(name, time);
                addList(ddl);
                System.out.println("Got it. I've added this task:");
                System.out.println(ddl);
                System.out.println("Now you have " + list.size() + " tasks in the list");
                System.out.println(LINE);
            }
            else {
                addList(new Task(input));
            }
        }
        bye();
    }

    public static void main(String[] args) {
        run();
    }
}
