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

    public static void add(String type, String text) throws AstraException {
        String argText;

        // validate args length
        if (text.length() > type.length() + 1) {
            argText = text.substring(type.length() + 1).strip();
            if (argText.isEmpty()) {
                throw new AstraException("The description of a task cannot be empty.");
            }
        } else {
            throw new AstraException("The description of a task cannot be empty.");
        }

        // build args map
        HashMap<String, String> args = getArgs(argText);

        // validate args existence
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

    public static void mark(int index) throws AstraException {
        Task t = tasks.get(index);
        t.setDone(true);
        String msg = " Nice! I've marked this task as done: \n  " + t + "\n";
        System.out.println(formatMsg(msg));
    }

    public static void unmark(int index) throws AstraException {
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
            try {
                if (command.equals("list")) {
                    listItems();
                } else if (command.equals("bye")) {
                    loop = false;
                } else if (command.equals("mark")) {
                    int index;
                    try {
                        index = Integer.parseInt(text.split(" ")[1]);
                    } catch (Exception e) {
                        throw new AstraException("Invalid index.");
                    }
                    mark(index);
                } else if (command.equals("unmark")) {
                    int index;
                    try {
                        index = Integer.parseInt(text.split(" ")[1]);
                    } catch (Exception e) {
                        throw new AstraException("Invalid index.");
                    }
                    unmark(index);
                } else if (command.equals("todo")) {
                    add("todo", text);
                } else if (command.equals("deadline")) {
                    add("deadline", text);
                } else if (command.equals("event")) {
                    add("event", text);
                } else {
                    throw new AstraException("Unknown command.");
                }
            } catch (AstraException e) {
                System.out.println(formatMsg(e.getMessage() + "\n"));
            }
        }
        goodbye();
    }
}
