import java.util.ArrayList;
import java.util.Scanner;

public class Elysia {
    static String line = "____________________________________________________________";
    static String welcomeMessage = "Hi there! Did you miss me?\n" +
            "Wherever you are and whenever you need,\n" +
            "Elysia will always meet your expectations.";
    static String exitMessage = "Alright, this time we really have to say goodbye.\n" +
            "Goodbye, Mei!";
    private static ArrayList<Task> arrayList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        arrayList = new ArrayList<>();

        System.out.println(line);
        System.out.println(welcomeMessage);
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(exitMessage);
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark ")) {
                markAsDone(input);
            } else if (input.startsWith("unmark ")) {
                unmarkAsDone(input);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {

                if (input.startsWith("todo")) {
                    addToDos(input.substring(4).trim());
                } else if (input.startsWith("deadline")) {
                    addDeadline(input.substring(8).trim());
                } else {
                    addEvent(input.substring(5).trim());
                }

            } else {
                System.out.println("Oh my! I'm so sorry,\n" +
                        "but it seems I'm not sure what that means.\n" +
                        "Let's figure it out together, shall we?");
            }
        }
    }

    public static void handleAddedMessage(Task task) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + arrayList.size() + " tasks in the list.");
        System.out.println(line);
    }

    public static void addToDos(String s) {
        if (s.isEmpty()) {
            handleEmptyDescription("todo");
        } else {
            Task task = new ToDos(s);
            arrayList.add(task);
            handleAddedMessage(task);
        }
    }

    //deadline return book /by Sunday
    public static void addDeadline(String s) {
        if (s.isEmpty()) {
            handleEmptyDescription("deadline");
        } else {
            String[] str = s.split("/by ");
            Task task = new Deadline(str[0], str[1]);
            arrayList.add(task);
            handleAddedMessage(task);
        }
    }

    public static void addEvent(String s) {
        if (s.isEmpty()) {
            handleEmptyDescription("event");
        } else {
            String[] str = s.split("/from | /to ");
            Task task = new Event(str[0], str[1], str[2]);
            arrayList.add(task);
            handleAddedMessage(task);
        }
    }

    public static void printList() {
        int n = arrayList.size();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= n; i++) {
            Task curr = arrayList.get(i - 1);
            System.out.println(i + "." + curr.toString());
        }
    }

    public static void markAsDone(String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(5))) - 1;
        Task curr = arrayList.get(index);
        curr.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(curr);
    }

    public static void unmarkAsDone(String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
        Task curr = arrayList.get(index);
        curr.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(curr);
    }

    public static void handleEmptyDescription(String taskType) {
        System.out.println(line);
        System.out.println("Oopsie! It looks like the description for this " +
                taskType +
                " is missing.\n" +
                "How about we add a little something to it?");
        System.out.println(line);
    }
}

