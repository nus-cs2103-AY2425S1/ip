import java.util.Scanner;
import java.util.ArrayList;

public class ThatOneGuy {
    private static String line = "____________________________________________________________";
    private static ArrayList<String> tasks = new ArrayList<String>();
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
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.valueOf(i + 1) + ". " + tasks.get(i));
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
            }
            else if (next.equals("list")) {
                list();
            }
            else {
                tasks.add(next);
                System.out.println(line + "\n" + "added: " + next + "\n" + line + "\n");
            }
        }
    }
    public static void main(String[] args) {
        greet();
        cmd();
        farewell();
    }
}
