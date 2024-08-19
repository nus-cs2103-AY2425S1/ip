import java.util.Scanner;
import java.util.ArrayList;
public class Dook {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>(100);
    private static final String separator = "____________________________________________________________";
    private static final String greeting = "Hello! I'm Dook\nWhat can I do for you?\n" + separator;
    private static final String exit = "Bye. Hope to see you again soon!\n" + separator;


    public static void start() {
        System.out.println(separator);
        System.out.println(greeting);
    }
    public static void end() {
        System.out.println(separator);
        System.out.println(exit);
    }

    public static void list() {
        System.out.println(separator);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i -1 ));
        }
        System.out.println(separator);
    }

    public static void markDone(int taskNumber) throws DookException {
        if (taskNumber >= tasks.size()) {
            throw new DookException("You don't have that many tasks");
        }
        tasks.get(taskNumber).markAsDone();
        System.out.println(separator);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber));
        System.out.println(separator);
    }

    public static void unmark(int taskNumber) throws DookException {
        if (taskNumber >= tasks.size()) {
            throw new DookException("You don't have that many tasks");
        }
        tasks.get(taskNumber).unmark();
        System.out.println(separator);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber));
        System.out.println(separator);
    }

    public static void createTodo(String description) throws DookException {
        if (description.isEmpty()) {
            throw new DookException("Need a description for your todo");
        }
        Task todo = new Todo(description);
        tasks.add(todo);
        System.out.println(separator);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(separator);
    }

    public static void createDeadline(String description, String by) throws DookException {
        if (description.isEmpty()) {
            throw new DookException("Need a description for your deadline");
        } else if (by.isEmpty()) {
            throw new DookException("Need a due date for your deadline");
        }
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        System.out.println(separator);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(separator);
    }

    public static void createEvent(String description, String start, String end) throws DookException {
        if (description.isEmpty()) {
            throw new DookException("Need a description for your event");
        } else if (start.isEmpty() && end.isEmpty()) {
            throw new DookException("Need a timeline for your event");
        } else if (start.isEmpty()) {
            throw new DookException("Need a start time for your event");
        } else if (end.isEmpty()) {
            throw new DookException("Need an end time for your event");
        }

        Task event = new Event(description, start, end);
        tasks.add(event);
        System.out.println(separator);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(separator);
    }

    public static void delete(int taskNumber) throws DookException {
        if (taskNumber >= tasks.size()) {
            throw new DookException("You don't have that many tasks");
        }

        Task removed = tasks.remove(taskNumber - 1);
        System.out.println(separator);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removed);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(separator);
    }

    public static void main(String[] args) {

        start();

        String response;

        while (true) {
            response = scanner.nextLine();
            try {
                if (response.equals("bye")) {
                    end();
                    break;
                } else if (response.equals("list")) {
                    list();
                } else if (response.matches("^mark \\d+$")) {
                    int number = Integer.parseInt(response.trim().split(" ")[1]) - 1;
                    markDone(number);
                } else if (response.matches("^unmark \\d+$")) {
                    int number = Integer.parseInt(response.trim().split(" ")[1]) - 1;
                    unmark(number);
                } else if (response.startsWith("todo")) {
                    String description = response.substring(4).trim();
                    createTodo(description);

                } else if (response.startsWith("deadline")) {
                    String[] deadlineInput = response.substring(8).split(" /by ", 2);
                    if (deadlineInput.length == 2) {
                        String description = deadlineInput[0].trim();
                        String by = deadlineInput[1].trim();
                        createDeadline(description, by);
                        continue;
                    }
                    throw new DookException("Invalid command, check your formatting");

                } else if (response.startsWith("event")) {
                    String[] eventInput = response.substring(5).split(" /from ", 2);
                    if (eventInput.length == 2) {
                        String description = eventInput[0].trim();
                        String[] timings = eventInput[1].split(" /to ", 2);
                        if (timings.length == 2) {
                            String start = timings[0].trim();
                            String end = timings[1].trim();
                            createEvent(description, start, end);
                            continue;
                        }
                    }
                    throw new DookException("Invalid command, check your formatting");

                } else if (response.matches("^delete \\d+$")) {
                    int number = Integer.parseInt(response.trim().split(" ")[1]);
                    delete(number);
                } else {
                    throw new DookException("Invalid command");
                }

            } catch (DookException e) {
                System.out.println(separator);
                System.out.println(e.getMessage());
                System.out.println(separator);
            }

        }
    }
}
