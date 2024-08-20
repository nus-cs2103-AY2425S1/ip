import java.util.Scanner;
import java.util.ArrayList;

public class ThatOneGuy {
    private static String line = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void greet() {
        String name = "that one guy";
        System.out.println(line + "\nI'm " + name + ".");
        System.out.println("Make it quick, I don't have much time.\n" + line);
    }

    public static void farewell() {
        System.out.println(line + "\nWhatever. Hope you never come back.\n"  + line);
    }

    public static void list() {
        System.out.println(line);
        int len = tasks.size();
        if (len == 0) {
            System.out.println("You really don't have anything better to do?");
        }
        else {
            System.out.println("Here are your damned tasks. Complete them or something.");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.println(line);
    }

    public static void cmd() {
        Scanner sc = new Scanner(System.in);
        String next;
        while (true) {
            next = sc.nextLine();
            if (next.equals("bye")) {
                break;
            } else if (next.equals("list")) {
                list();
            } else if (next.startsWith("mark ")) {
                Integer id = Integer.parseInt(next.substring(5)) - 1;
                if (id < 0 || id >= tasks.size()) {
                    System.out.println(line + '\n' + "That's not a valid ID, bozo.");
                } else {
                    tasks.get(id).mark();
                    System.out.println(line);
                    System.out.println("Eh. Consider this task done:");
                    System.out.println(tasks.get(id).toString());
                    System.out.println(line);
                }

            } else if (next.startsWith("unmark ")) {
                Integer id = Integer.parseInt(next.substring(7)) - 1;
                if (id < 0 || id >= tasks.size()) {
                    System.out.println(line + '\n' + "That's not a valid ID, bozo.");
                } else {
                    tasks.get(id).unmark();
                    System.out.println(line);
                    System.out.println("Sucks to be you. Looks like you haven't done this task:");
                    System.out.println(tasks.get(id).toString());
                    System.out.println(line);
                }
            } else {
                Task nextTask = new Task(next);
                tasks.add(nextTask);
                System.out.println(line + "\n" + "added: " + next);
                System.out.println("That's " + tasks.size() + " tasks for your ass to handle.\n" + line);
            }
        }
        sc.close();
    }
    public static void main(String[] args) {
        greet();
        cmd();
        farewell();
    }
}
