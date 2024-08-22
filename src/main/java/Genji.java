import java.util.ArrayList;
import java.util.Scanner;

public class Genji {
    private static String NAME = "Genji";
    private static String LINE = "________________________________________________________________";
    private static Scanner scanner = new Scanner(System.in);
    private static boolean flag = true;
    private static ArrayList<String> list = new ArrayList();

    public static void addList(String s) {
        list.add(s);
        System.out.println("added: " + s);
        System.out.println(LINE);
    }

    public static void showList() {
        int index = 1;
        for(String task : list) {
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
            } else {
                addList(input);
            }
        }
        bye();
    }

    public static void main(String[] args) {
        run();
    }
}
