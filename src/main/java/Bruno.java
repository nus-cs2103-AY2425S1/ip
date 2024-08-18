import java.util.ArrayList;
import java.util.Scanner;

public class Bruno {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Task> taskList = new ArrayList<>();
    enum TaskType {
        TODO, DEADLINE, EVENT
    }

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

            try {
                if (userResponse.equals("bye")) {
                    running = false;
                    printByeMessage();
                } else if (userResponse.equals("list")) {
                    printList();
                } else if (firstWord.equals("mark")) {
                    markTask(restOfString);
                } else if (firstWord.equals("unmark")) {
                    unmarkTask(restOfString);
                } else if (firstWord.equals("delete")) {
                    deleteTask(restOfString);
                } else if (firstWord.equals("todo")) {
                    addTask(restOfString, TaskType.TODO);
                } else if (firstWord.equals("deadline")) {
                    addTask(restOfString, TaskType.DEADLINE);
                } else if (firstWord.equals("event")) {
                    addTask(restOfString, TaskType.EVENT);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (BrunoException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
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

    public static void addTask(String str, TaskType type) throws BrunoException {
        if (str.trim().isEmpty()) {
            throw new EmptyTaskException();
        }

        Task task = null;
        boolean recognized = true;
        if (type.equals(TaskType.TODO)) {
            task = new ToDo(str);
        } else if (type.equals(TaskType.DEADLINE)) {
            if (!str.contains("/by")) {
                throw new MissingFieldException();
            }
            String description = str.substring(0, str.indexOf("/by")).trim();
            String by = str.substring(str.indexOf("/by") + 4).trim();
            task = new Deadline(description, by);
        } else if (type.equals(TaskType.EVENT)) {
            if (!str.contains("/from") || !str.contains("/to")) {
                throw new MissingFieldException();
            }
            String description = str.substring(0, str.indexOf("/from")).trim();
            String from = str.substring(str.indexOf("/from") + 6, str.indexOf("/to")).trim();
            String to = str.substring(str.indexOf("/to") + 4).trim();
            task = new Event(description, from, to);
        } else {
            recognized = false;
        }

        if (recognized) {
            taskList.add(task);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:\n" + task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            throw new UnknownCommandException();
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

    public static void markTask(String num) throws BrunoException {
        try {
            Task task = taskList.get(Integer.parseInt(num) - 1);
            task.complete();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:\n" + task);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public static void unmarkTask(String num) throws BrunoException {
        try {
            Task task = taskList.get(Integer.parseInt(num) - 1);
            task.uncomplete();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've unmarked this task as done:\n" + task);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public static void deleteTask(String num) throws BrunoException {
        try {
            Task task = taskList.remove(Integer.parseInt(num) - 1);
            System.out.println("____________________________________________________________");
            System.out.println("Noted! I've removed this task:\n" + task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }
}
