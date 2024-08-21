import java.util.HashMap;
import java.util.Scanner;

public class Astra {
    private static final TaskList tasks = new TaskList();

    private static String formatMsg(String msg) {
        return "____________________________________________________________\n" +
                msg +
                "____________________________________________________________\n";
    }

    private static HashMap<String, String> getArgs(String text) {
        String[] words = text.split(" ");
        HashMap<String, String> args = new HashMap<>();
        String key = "main";
        for (String word : words) {
            if (word.charAt(0) == '/') {
                key = word.substring(1);
            } else {
                if (args.containsKey(key)) {
                    args.put(key, args.get(key) + " " + word);
                } else {
                    args.put(key, word);
                }
            }
        }
        return args;
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

    public static void add(String type, String text) {
        HashMap<String, String> args = getArgs(text);
        Task t = null;
        switch (type) {
            case "todo" -> t = new Todo(args.get("main"));
            case "deadline" -> t = new Deadline(args.get("main"), args.get("by"));
            case "event" -> t = new Event(args.get("main"), args.get("from"), args.get("to"));
        }
        if (t == null) {
            return;
        }
        tasks.add(t);
        String msg = "Got it. I've added this task: \n  " +
                    t + "\n" +
                    "Now you have " + tasks.length() + " tasks in the list. \n";

        System.out.println(formatMsg(msg));
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
        String text;
        boolean loop = true;

        greet();
        while (loop) {
            text = inp.nextLine();
            String command = text.split(" ")[0];
            switch (command) {
                case "list":
                    listItems();
                    break;
                case "bye":
                    loop = false;
                    break;
                case "mark":
                    mark(Integer.parseInt(text.split(" ")[1]));
                    break;
                case "unmark":
                    unmark(Integer.parseInt(text.split(" ")[1]));
                    break;
                case "todo":
                    add("todo", text.substring(command.length() + 1));
                    break;
                case "deadline":
                    add("deadline", text.substring(command.length() + 1));
                    break;
                case "event":
                    add("event", text.substring(command.length() + 1));
                    break;
                default:
                    System.out.println("unknown command");
                    break;
            }
        }
        goodbye();
    }
}
