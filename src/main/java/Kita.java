import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kita {
    private static void printLine() {
        System.out.println("____________________________________________________________\n");
    }
    private final static ArrayList<Task> commandsList = new ArrayList<>();
    private final static Pattern toDoPattern = Pattern.compile("^todo (.+)$");
    private final static Pattern deadlinePattern = Pattern.compile("^deadline (.+) /by (.+)$");
    private final static Pattern eventPattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");

    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);

        printLine();
        System.out.println(" Hello! I'm Kita");
        System.out.println(" What can I do for you?");
        printLine();
        while (true) {
            String command = getInput.nextLine();
            printLine();
            if (command.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!\n");
                break;
            }
            else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= commandsList.size(); i++) {
                    System.out.println(i + ". " + commandsList.get(i-1));
                }
            }
            else if (command.startsWith("mark")) {
                int numberToMark = Integer.parseInt(command.split(" ")[1]);
                System.out.println("Nice! I've marked this task as done:");
                Task selectedTask = commandsList.get(numberToMark-1);
                selectedTask.setCompleted(true);
                System.out.println("  " + selectedTask);
            }
            else if (command.startsWith("unmark")) {
                int numberToMark = Integer.parseInt(command.split(" ")[1]);
                System.out.println("OK, I've marked this task as not done yet:");
                Task selectedTask = commandsList.get(numberToMark-1);
                selectedTask.setCompleted(false);
                System.out.println("  " + selectedTask);
            }
            else {
                Matcher eventMatcher = eventPattern.matcher(command);
                Matcher deadlineMatcher = deadlinePattern.matcher(command);
                Matcher todoMatcher = toDoPattern.matcher(command);
                Task newTask;

                if (eventMatcher.find()) {
                    newTask = new Event(eventMatcher.group(1), eventMatcher.group(2), eventMatcher.group(3));
                }
                else if (deadlineMatcher.find()) {
                    newTask = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
                }
                else if (todoMatcher.find()) {
                    newTask = new ToDo(todoMatcher.group(1));
                }
                else {
                    // Invalid error
                    System.out.println("Invalid event type specified");
                    continue;
                }
                commandsList.add(newTask);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + commandsList.size() + " tasks in the list.");

            }

            printLine();
        }
    }
}
