import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Topaz {

    private static ArrayList<Task> taskList = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greetUser();

        while (true) {
            String prompt = scanner.nextLine();
            try {
                if (prompt.equalsIgnoreCase("help")) {
                    showHelp();
                } else if (prompt.contains("bye")) {
                    goodbyeUser();
                    break;
                } else if (prompt.equals("list")) {
                    listTasks();
                } else if (prompt.startsWith("mark")) {
                    int index = Integer.parseInt(prompt.substring(5));
                    markTask(index);
                } else if (prompt.startsWith("unmark")) {
                    int index = Integer.parseInt(prompt.substring(7));
                    unmarkTask(index);
                } else if (prompt.startsWith("todo")) {
                    addTodo(prompt);
                } else if (prompt.startsWith("deadline")) {
                    addDeadline(prompt);
                } else if (prompt.startsWith("event")) {
                    addEvent(prompt);
                } else if (prompt.startsWith("delete")) {
                    int index = Integer.parseInt(prompt.substring(7));
                    deleteTask(index);
                } else {
                    throw new TopazException(prompt);
                }
            } catch (IndexOutOfBoundsException e) {
                handleIoBException(e);
            } catch (InvalidTaskException | TopazException e) {
                handleException(e);
            }
        }

        scanner.close();
    }

    private static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Topaz - I work in the IPC's Strategic Investment Department. Long time no see! How have you been? Clocking in a lot of overtime?");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void goodbyeUser() {
        System.out.println(" Ugh, why do they have to call me for such a trivial matter... Sorry, something just came up at work, I've got to get going!");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void echo(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }

    private static void addTasks(Task task) {
        task.addToList(taskList);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("    " + task.getStatus());
        System.out.println(" Hard work pays off~");
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Who works for money these days? Money is a means, not an end. Work should make you happy... That's the most fundamental principle.");
        System.out.println(" Don't forget your work~");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).getStatus());
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTask(int index) {
        Task task = taskList.get(index - 1);
        task.setDone();
        System.out.println("____________________________________________________________");
        System.out.println(" Another project over the finish line! I've marked this task as done:");
        System.out.println("    " + task.getStatus());
        System.out.println("____________________________________________________________");
    }

    private static void unmarkTask(int index) {
        Task task = taskList.get(index - 1);
        task.setUndo();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("    " + task.getStatus());
        System.out.println("____________________________________________________________");
    }

    private static void deleteTask(int index) {
        Task task = taskList.remove(index - 1);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("    " + task.getStatus());
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void addTodo(String prompt) throws InvalidTaskException {
        try {
            String description = prompt.substring(5);
            Todo todo = new Todo(description);
            addTasks(todo);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException("todo");
        }
    }

    private static void addDeadline(String input) throws InvalidTaskException{
        String deadlinePattern = "deadline (.*?) /by";
        String byPattern = "/by (.*)";

        Pattern ddlPatternCompiled = Pattern.compile(deadlinePattern);
        Pattern byPatternCompiled = Pattern.compile(byPattern);

        Matcher deadlineMatcher = ddlPatternCompiled.matcher(input);
        Matcher byMatcher = byPatternCompiled.matcher(input);

        if (deadlineMatcher.find() && byMatcher.find()) {
            String description = deadlineMatcher.group(1).trim();
            String by = byMatcher.group(1).trim();
            Deadline deadline = new Deadline(description, by);
            addTasks(deadline);
        } else {
            throw new InvalidTaskException("deadline");
        }
    }

    private static void addEvent(String input) throws InvalidTaskException{
        String eventPattern = "event (.*?) /from";
        String fromPattern = "/from (.*?) /to";
        String toPattern = "/to (.*)";

        Pattern eventPatternCompiled = Pattern.compile(eventPattern);
        Pattern fromPatternCompiled = Pattern.compile(fromPattern);
        Pattern toPatternCompiled = Pattern.compile(toPattern);

        Matcher eventMatcher = eventPatternCompiled.matcher(input);
        Matcher fromMatcher = fromPatternCompiled.matcher(input);
        Matcher toMatcher = toPatternCompiled.matcher(input);

        if (eventMatcher.find() && fromMatcher.find() && toMatcher.find()) {
            String description = eventMatcher.group(1).trim();
            String from = fromMatcher.group(1).trim();
            String to = toMatcher.group(1).trim();
            Event event = new Event(description, from, to);
            addTasks(event);
        } else {
            throw new InvalidTaskException("event");
        }
    }

    private static void handleException(Exception e) {
        System.out.println("____________________________________________________________");
        System.out.println(e);
        System.out.println("Try again!");
        System.out.println("____________________________________________________________");
    }

    private static void handleIoBException(Exception e) {
        System.out.println("____________________________________________________________");
        System.out.println("Please enter valid information after the keyword!");
        System.out.println("Try again!");
        System.out.println("____________________________________________________________");
    }
    private static void showHelp() {
        String guide = """
                 1. Adding Tasks
                    1) ToDos: Tasks without specific dates/times.
                    Command: todo <description>
                    Example: todo borrow book
                    Response: Confirms the addition and displays the task.
                    
                    2) Deadlines: Tasks that must be completed by a specific date/time.         
                    Command: deadline <description> /by <date/time>
                    Example: deadline return book /by Sunday
                    Response: Confirms the addition and shows the deadline.
                    
                    3) Events: Tasks that start and end at specific date/times.         
                    Command: event <description> /from <start date/time> /to <end date/time>
                    Example: event project meeting /from Mon 2pm /to 4pm
                    Response: Confirms the addition and shows the event details.
                 2. Viewing All Tasks
                    Command: list
                    Response: Displays all tasks in your list with their types and statuses.
                 3. Mark task status
                    1) Mark task as done: mark <index of task in list>
                    2) Mark task as undo: unmark <index of task in list>""";
        System.out.println("____________________________________________________________");
        System.out.println(guide);
        System.out.println("____________________________________________________________");
    }
}
