import java.util.Scanner;

public class Astra {
    private static final TaskList tasks = new TaskList();

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
        tasks.add(item);
        System.out.println(formatMsg("added: " + item + "\n"));
    }

    public static void listItems() {
        System.out.println(formatMsg(tasks.toString()));
    }

    public static void mark(int index) {
        Task t = tasks.get(index);
        t.setDone(true);
        String msg = " Nice! I've marked this task as done: \n  " + t + "\n";
        System.out.println(formatMsg(msg));
    }

    public static void unmark(int index) {
        Task t = tasks.get(index);
        t.setDone(false);
        String msg = " OK, I've marked this task as not done yet: \n  " + t + "\n";
        System.out.println(formatMsg(msg));
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
            } else if (text.split(" ")[0].equals("mark")) {
                mark(Integer.parseInt(text.split(" ")[1]));
            } else if (text.split(" ")[0].equals("unmark")) {
                unmark(Integer.parseInt(text.split(" ")[1]));
            } else {
                add(text);
            }
        }
        goodbye();
    }
}
