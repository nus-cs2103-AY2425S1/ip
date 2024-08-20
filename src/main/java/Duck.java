import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duck {
    static ArrayList<Task> tasks = new ArrayList<>();
    enum Instruction {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        BYE
    }
    public static void main(String[] args) {
        String logo = """
                        ,---,                                  ,-. \s
                      .'  .' `\\                            ,--/ /| \s
                    ,---.'     \\          ,--,           ,--. :/ | \s
                    |   |  .`\\  |       ,'_ /|           :  : ' /  \s
                    :   : |  '  |  .--. |  | :    ,---.  |  '  /   \s
                    |   ' '  ;  :,'_ /| :  . |   /     \\ '  |  :   \s
                    '   | ;  .  ||  ' | |  . .  /    / ' |  |   \\  \s
                    |   | :  |  '|  | ' |  | | .    ' /  '  : |. \\ \s
                    '   : | /  ; :  | : ;  ; | '   ; :__ |  | ' \\ \\\s
                    |   | '` ,/  '  :  `--'   \\'   | '.'|'  : |--' \s
                    ;   :  .'    :  ,      .-./|   :    :;  |,'    \s
                    |   ,.'       `--`----'     \\   \\  / '--'      \s
                    '---'                        `----'            \s
                """;


        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duck");
        System.out.println("What can I do for you? \n");

        getInputTillBye();
        System.out.println("Bye. Hope to see you again soon!");

    }

    // obtain user input till he inputs bye, ignoring case
    public static void getInputTillBye() {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        while (!message.equalsIgnoreCase(Instruction.BYE.toString())) {
            reactTo(message);
            in = new Scanner(System.in);
            message = in.nextLine();
        }
    }

    public static void reactTo(String message) {
        try {
            Instruction instruction = Instruction.valueOf(getInstruction(message));
            switch (instruction) {
                case LIST:
                    listInstruction();
                    break;
                case MARK, UNMARK:
                    updateStatus(message);
                    break;
                case TODO:
                    toDoInstruction(message);
                    break;
                case DEADLINE:
                    deadlineInstruction(message);
                    break;
                case EVENT:
                    eventInstruction(message);
                    break;
                default:
                    break;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Hey, that's not a valid instruction! I don't know how to respond to that.");
        }
    }

    public static String getInstruction(String message) {
        return message.trim().split(" ")[0].toUpperCase();
    }

    public static ToDo parseToDo(String input) {
        // Normalize to lowercase and remove the todo keyword
        String description = input.replaceFirst("(?i)^todo\\s+", "").trim();
        return new ToDo(description);
    }

    public static Deadline parseDeadline(String input) {
        // Regular expression to match the pattern for deadline
        Pattern pattern = Pattern.compile("(?i)^deadline\\s+(.+)\\s+/by\\s+(.+)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadline = matcher.group(2);
            return new Deadline(description, deadline);
        }
        else {
            return new Deadline("invalid", "invalid");
        }
    }

    public static Event parseEvent(String input) {
        // Regular expression to match the pattern for event
        Pattern pattern = Pattern.compile("(?i)^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return new Event(description, from, to);
        } else {
            return new Event("invalid", "invalid", "invalid");
        }

    }

    public static void listInstruction() {
        int idx = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks) {
            System.out.println(idx++ + "." + t.toString());
        }
    }

    public static void toDoInstruction(String message) {
        ToDo todo = parseToDo(message);
        addTask(todo);
    }

    public static void deadlineInstruction(String message) {
        Deadline deadline = parseDeadline(message);
        addTask(deadline);
    }

    public static void eventInstruction(String message) {
        Event event = parseEvent(message);
        addTask(event);
    }

    public static boolean isUpdatingStatus(String message) {
        String[] words = message.split(" ");
        return words.length == 2
                && (words[0].equals("mark") || words[0].equals("unmark"))
                && isInteger(words[1]);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void updateStatus(String message) {
        if (!isUpdatingStatus(message)) {
            return;
        }

        String[] words = message.split(" ");
        try {
            if (words[0].equals("mark")) {
                tasks.get(Integer.parseInt(words[1]) - 1).markAsDone();
            } else {
                tasks.get(Integer.parseInt(words[1]) - 1).markAsIncomplete();
            }
        } catch (NumberFormatException e) {
            System.out.println("Oops! you have to indicate a valid task index!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Index out of bound :( Input a valid task index.");
        }
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }


}
