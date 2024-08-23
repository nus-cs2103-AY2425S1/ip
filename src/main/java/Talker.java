import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Talker {

    private static final String NAME = "Talker";
    private static final String LINE = "____________________________________________________________";

    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(LINE);
        System.out.printf("Hello! I'm %s\n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Scanner reader = new Scanner(System.in);


        // read user input
        String input = reader.nextLine();
        System.out.println(LINE);

        // if command "bye" entered, exit
        while (!input.equals("bye")) {
            try {
                inputManager(input);
            } catch (TalkerException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println(LINE);
                input = reader.nextLine();
                System.out.println(LINE);
            }
        }

        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(LINE);
    }

    private static void inputManager(String input) throws TalkerException {
        String[] parsed = input.split(" ");

        switch (parsed[0]) {
            case "list":
                listTasks();
                break;
            case "mark":
                markTaskComplete(parsed);
                break;
            case "unmark":
                unmarkTaskComplete(parsed);
                break;
            case "delete":
                deleteTask(parsed);
                break;
            case "todo":
                createToDo(input);
                break;
            case "deadline":
                createDeadline(input);
                break;
            case "event":
                createEvent(input);
                break;
            default:
                throw new TalkerException("Unknown command!");
        }
    }

    private static void listTasks() throws TalkerException {
        if (list.isEmpty()) {
            throw new TalkerException("List is empty!");
        }

        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, list.get(i));
        }
    }

    private static void markTaskComplete(String[] parsed) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            System.out.println(list.get(index).mark());
        } catch (NumberFormatException e) {
            throw new TalkerException("Mark format wrong. Try again with: mark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
        throw new TalkerException("Task not found!");
        }
    }

    private static void unmarkTaskComplete(String[] parsed) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            System.out.println(list.get(index).unmark());
        } catch (NumberFormatException e) {
            throw new TalkerException("Unmark format wrong. Try again with: unmark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    private static void deleteTask(String[] parsed) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;

            Task removed = list.remove(index);

            System.out.println("Noted. I've removed this task:");
            System.out.println(removed);
            System.out.printf("Now you have %d tasks in the list. \n", list.size());
        } catch (NumberFormatException e) {
            throw new TalkerException("Delete format wrong. Try again with: delete <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    private static void createToDo(String input) throws TalkerException {
        try {
            String desc = input.substring(5);

            Task newTask = new ToDo(desc);

            list.add(newTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list. \n", list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException("ToDo must have a description");
        }
    }

    private static void createDeadline(String input) throws TalkerException {
        try {
            String contents = input.substring(9);

            String[] parsed = contents.split(" /by ", 2);
            String desc = parsed[0];
            String by = parsed[1];

            Task newTask = new Deadline(desc, by);

            list.add(newTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list. \n", list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException("Deadline format wrong. Try again with: deadline <description> /by <deadline>");
        }
    }

    private static void createEvent(String input) throws TalkerException {
        try {
            String contents = input.substring(6);

            String[] parsed1 = contents.split(" /from ", 2);
            String[] parsed2 = parsed1[1].split(" /to ", 2);
            String desc = parsed1[0];
            String from = parsed2[0];
            String to = parsed2[1];

            Task newTask = new Event(desc, from, to);

            list.add(newTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list. \n", list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException(
                    "Event format wrong. Try again with: event <description> /from <start> /to <end>");
        }
    }
}

