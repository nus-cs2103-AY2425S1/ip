import java.util.Scanner;

public class ThatOneGuy {
    private static String line = "____________________________________________________________";
    public static void greet() {
        String name = "That one guy";
        System.out.println(line + "\nHello! I'm " + name);
        System.out.println("What can I do for you?\n" + line);
    }

    public static void farewell() {
        System.out.println(line + "\nBye.  Hope to see you again soon!\n"  + line);
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String next;
        while (true) {
            next = sc.nextLine();
            if (next.equals("bye")) {
                break;
            }
            System.out.println(line + "\n" + next + "\n" + line + "\n");
        }
    }
    public static void main(String[] args) {
        greet();
        echo();
        farewell();
    }
}
