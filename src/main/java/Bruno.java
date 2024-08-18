import java.util.ArrayList;
import java.util.Scanner;

public class Bruno {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        String userResponse;

        printGreetingMessage();

        boolean running = true;
        while (running) {
            userResponse = input.nextLine();
            String firstWord;
            String restOfString = "";

            if (userResponse.contains(" ")) {
                firstWord = userResponse.substring(0, userResponse.indexOf(" "));
                restOfString = userResponse.split(" ", 2)[1];
            } else {
                firstWord = userResponse;
            }

            if (userResponse.equals("bye")) {
                running = false;
                printByeMessage();
            } else if (userResponse.equals("list")) {
                printList();
            } else if (firstWord.equals("mark")) {
                markTask(restOfString);
            } else if (firstWord.equals("unmark")) {
                unmarkTask(restOfString);
            } else if (firstWord.equals("todo")) {
                addTask(restOfString, firstWord);
            } else if (firstWord.equals("deadline")) {
                addTask(restOfString, firstWord);
            } else if (firstWord.equals("event")) {
                addTask(restOfString, firstWord);
            } else {
                System.out.println("Sorry, I could not understand");
            }
        }
    }

    public static void printGreetingMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bruno\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String str, String type) {
        Task task = null;
        boolean recognized = true;
        if (type.equals("todo")) {
            task = new ToDo(str);
        } else if (type.equals("deadline")) {
            String description = str.substring(0, str.indexOf(" /by"));
            String by = str.substring(str.indexOf(" /by") + 5);
            task = new Deadline(description, by);
        } else if (type.equals("event")) {
            String description = str.substring(0, str.indexOf(" /from"));
            String from = str.substring(str.indexOf(" /from") + 7, str.indexOf("/to"));
            String to = str.substring(str.indexOf(" /to") + 5);
            task = new Event(description, from, to);
        } else {
            recognized = false;
        }

        if (recognized) {
            taskList.add(task);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:\n" + task);
            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Event type not recognized");
            System.out.println("____________________________________________________________");
        }
    }

    public static void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + "." + task);
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTask(String num) {
        Task task = taskList.get(Integer.parseInt(num) - 1);
        task.complete();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println("____________________________________________________________");
    }

    public static void unmarkTask(String num) {
        Task task = taskList.get(Integer.parseInt(num) - 1);
        task.uncomplete();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println("____________________________________________________________");
    }
}
