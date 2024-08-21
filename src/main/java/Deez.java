import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Deez {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static boolean isActive = true;
    static void say(String... msgs) {
        System.out.println("____________________________________________________________");
        for (String msg: msgs) {
            System.out.println("> " + msg);
        }
        System.out.println("____________________________________________________________");
    }

    static int parseInt(String s) throws DeezException {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            throw new DeezException("Invalid input!", "Please enter a valid number.");
        }
    }

    public static void exit() {
        isActive = false;
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            say("<No items in list>");
            return;
        }
        // Print list
        int cnt = 1;
        System.out.println("____________________________________________________________");
        for (Task t: tasks) {
            System.out.println(cnt + ". " + t.toString());
            cnt+=1;
        }
        System.out.println("____________________________________________________________");
    }

    public static void invalidCommand() throws DeezException {
       throw new DeezException("Please enter a valid command.");
    }

    public static void addTodo(String param) throws DeezException {
        if (param.isBlank()) {
            throw new DeezException("Please provide a description for the todo.");
        }
        Todo t = new Todo(param.strip());
        tasks.add(t);
        say("Easy. I have added your task.",
                t.toString(),
                "You have " + tasks.size() + " tasks in the " +
                        "list");
    }
    public static void addDeadline(String params) throws DeezException {
        if (params.isEmpty() || !params.contains("/by")) {
            throw new DeezException("Please provide a description and deadline.", "Usage:", "deadline " +
                    "return book" +
                    " /by Sunday");
        }
        String[] parts = params.split("/by", 2);
        if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new DeezException("Description and deadline must not be blank.", "Usage:", "deadline " +
                    "return book" +
                    " /by Sunday");
        }
        Deadline d = new Deadline(parts[0].strip(), parts[1].strip());
        tasks.add(d);
        say("Donezo. I have added your task.",
                d.toString(),
                "You have " + tasks.size() + " tasks in the " +
                        "list");
    }
    public static void addEvent(String params) throws DeezException {
        if (params.isEmpty() || !params.contains("/from") || !params.contains("/to")) {
            throw new DeezException("Please provide a description, start date, and end date.", "Usage:", "event " +
                    "project meeting /from Mon 2pm /to 4pm");
        }
        String[] parts = params.split("/from|/to", 3);
        if (parts.length != 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
            throw new DeezException("Description start date, and end date must not be blank.", "Usage:", "event " +
                    "project meeting /from Mon 2pm /to 4pm");
        }
        Event e = new Event(parts[0].strip(), parts[1].strip(), parts[2].strip());
        tasks.add(e);
        say("Event added",
                e.toString(),
                "You have " + tasks.size() + " tasks in the " +
                        "list");
    }

    public static void handleMarkUnmarkDone(boolean isMarkDone, String param) throws DeezException {
        int taskIdx = parseInt(param);
        try {
            Task t = tasks.get(taskIdx-1);
            if (isMarkDone && !t.isDone()) {
                t.toggleDone();
            } else if (!isMarkDone && t.isDone()) {
                t.toggleDone();
            }
            say("Updated task:", t.toString());
        } catch (Exception e) {
            throw new DeezException("No task at index " + param, "Please try again.");
        }
    }

    public static void deleteTask(String param) throws DeezException {
        int taskIdx = parseInt(param);
        try {
            Task t = tasks.get(taskIdx-1);
            tasks.remove(t);
            say("Deleted task:", t.toString(), tasks.size() + " tasks remain.");
        } catch (Exception e) {
            throw new DeezException("No task at index " + param, "Please try again.");
        }
    }

    public static void handleCommand(Command cmd, String params) {
        switch (cmd) {
            case EXIT -> exit();
            case LIST -> listTasks();
            case TODO -> addTodo(params);
            case DEADLINE -> addDeadline(params);
            case EVENT -> addEvent(params);
            case MARK -> handleMarkUnmarkDone(true, params);
            case UNMARK -> handleMarkUnmarkDone(false, params);
            case DELETE -> deleteTask(params);
            case UNKNOWN -> invalidCommand();
        }
    }
    public static void main(String[] args) throws IOException {
        say("Hello! I'm Deez!", "What can I do you for?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (isActive) {
            // process command
            String inputStr = br.readLine();

            if (inputStr.isEmpty()) {
                continue;
            }

            String[] inputStringSplit = inputStr.split(" ", 2);
            String cmdString = inputStringSplit[0];
            String params = inputStringSplit.length == 2 ? inputStringSplit[1]: "";

            Command cmd = switch (cmdString) {
                case "bye" -> Command.EXIT;
                case "list" -> Command.LIST;
                case "mark" -> Command.MARK;
                case "unmark" -> Command.UNMARK;
                case "todo" -> Command.TODO;
                case "deadline" -> Command.DEADLINE;
                case "event" -> Command.EVENT;
                case "delete" -> Command.DELETE;
                default -> Command.UNKNOWN;
            };
            try {
                handleCommand(cmd, params);
            } catch (DeezException e) {
                say(e.getErrorMessages());
            }
        }
        say("Bye. Hope to see you soon!");
    }
}
