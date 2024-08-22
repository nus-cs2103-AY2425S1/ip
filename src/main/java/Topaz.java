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
            if (prompt.contains("bye")) {
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
                String description = prompt.substring(5);
                addTodo(description);
            } else if (prompt.startsWith("deadline")) {
                addDeadline(prompt);
            } else if (prompt.startsWith("event")) {
                addEvent(prompt);
            } else {
                echo(prompt);
            }
        }

        scanner.close();
    }

    private static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Topaz — I work in the IPC's Strategic Investment Department. Long time no see! How have you been? Clocking in a lot of overtime?");
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
        System.out.println(" Got it. I've added this task: ");
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
        System.out.println(" Nice! I've marked this task as done: ");
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

    private static void addTodo(String description) {
        Todo todo = new Todo(description);
        addTasks(todo);
    }

    private static void addDeadline(String input) {
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
        }
    }

    private static void addEvent(String input) {
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
        }
    }
}
