import java.util.Scanner;

public class Astra {
    private static String[] items = new String[100];
    private static int count = 0;

    private static String formatMsg(String msg) {
        return "____________________________________________________________\n" +
                msg +
                "____________________________________________________________\n";
    }

    public static void greet() {
        String msg = """
                 Hello! I'm Astra.
                 What can I do for you?
                """;
        System.out.println(formatMsg(msg));
    }

    public static void goodbye() {
        String msg = " Bye. Hope to see you again soon!\n";
        System.out.println(formatMsg(msg));
    }

    public static void echo(String text) {
        System.out.println(formatMsg(text));
    }

    public static void add(String item) {
        items[count] = item;
        count++;
        System.out.println(formatMsg("added: " + item + "\n"));
    }

    public static void listItems() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < count; i++) {
            msg.append(String.format("%d. %s \n", i+1, items[i]));
        }
        System.out.println(formatMsg(msg.toString()));
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        String text = "";
        greet();
        while (true) {
            text = inp.nextLine();
            if (text.equals("list")) {
                listItems();
            } else if (text.equals("bye")) {
                break;
            } else {
                add(text);
            }
        }
        goodbye();
    }
}
