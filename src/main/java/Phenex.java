import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class Phenex {
    private final String line = "\t____________________________________________________________\n";
    private final ArrayList<Task> tasks;
    private final String logo = "  _____    _    _   ______   _   _   ______  __   __\n"
            + " |  __ \\  | |  | | |  ____| | \\ | | |  ____| \\ \\ / /\n"
            + " | |__) | | |__| | | |__    |  \\| | | |__     \\ V /\n"
            + " | |      | |  | | | |____  | |\\  | | |____   / . \\\n"
            + " |_|      |_|  |_| |______| |_| \\_| |______| /_/ \\_\\\n";
    private final String greetMsg = "Hello! I'm\n"
            + logo
            + "Your favourite solid gold mobile suit!\n"
            + line
            + "\tWhat can I do for you?\n"
            + line;
    private final String farewellMsg = "\t Goodbye. Extinguish the Zeon forces on your way out!\n" + line;

    public enum TaskType {
        TODO, DEADLINE, EVENT;
    }

    public Phenex() {
        this.tasks = new ArrayList<>();
    }

    public void greet() {
        System.out.print(greetMsg);
    }

    public void sayFarewell() {
        System.out.println(farewellMsg);
    }

    public void printLine() {
        System.out.print(line);
    }

    public void printTasks() {
        int size = this.tasks.size();
        if (size == 0) {
            System.out.println("\t No scheduled missions. Rest up for the next battle, soldier!");
            return;
        }

        System.out.println("\t Outstanding missions:");
        for (int i = 0; i < size; i++) {
            String row = "\t "
                    + (i + 1)
                    + "."
                    + tasks.get(i);
            System.out.println(row);
        }
    }

    public void markTaskCompleted(int idx) {
        if (idx >= this.tasks.size()) {
            System.out.println("\t Invalid input, no such mission!");
        } else {
            System.out.println("\t Mission marked as complete. Good job, soldier!");
            Task taskToMark = this.tasks.get(idx);
            taskToMark.markComplete();
            System.out.println("\t\t" + taskToMark);
        }
    }

    public void markTaskIncomplete(int idx) {
        if (idx >= this.tasks.size()) {
            System.out.println("\t Invalid input, no such mission!");
        } else {
            System.out.println("\t Mission marked as incomplete.");
            Task taskToUnmark = this.tasks.get(idx);
            taskToUnmark.markUncomplete();
            System.out.println("\t\t" + taskToUnmark);
        }
    }

    public void addTask(Matcher matcher, TaskType tt) {
        String taskName = matcher.group(1);
        String emptyNameRegex = "\\s*";
        Pattern emptyNamePattern = Pattern.compile(emptyNameRegex);
        Matcher emptyNameMatcher = emptyNamePattern.matcher(taskName);

        switch (tt) {
            case TODO:
                if (emptyNameMatcher.matches()) {
                    System.out.println("Error, invalid todo name");
                    break;
                }
                ToDo toDo = new ToDo(taskName);
                this.tasks.add(toDo);
                this.printTaskAdded(toDo);
                break;
            case DEADLINE:
                if (emptyNameMatcher.matches()) {
                    System.out.println("Error, invalid deadline name");
                    break;
                }
                String deadlineBy = matcher.group(2);
                Deadline deadline = new Deadline(taskName, deadlineBy);
                this.tasks.add(deadline);
                this.printTaskAdded(deadline);
                break;
            case EVENT:
                if (emptyNameMatcher.matches()) {
                    System.out.println("Error, invalid event name");
                    break;
                }
                String eventFrom = matcher.group(2);
                String eventTo = matcher.group(3);
                Event event = new Event(taskName, eventFrom, eventTo);
                this.tasks.add(event);
                this.printTaskAdded(event);
                break;
            default:
                System.out.println("Unknown input");
                break;
        }
    }

    public void printTaskAdded(Task task) {
        System.out.println("\t Mission " + task.name + " added:");
        System.out.println("\t   " + task);
        System.out.println("\t Total upcoming missions: " + this.tasks.size());
    }

    public static void main(String[] args) {
        Phenex p = new Phenex();
        p.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        // regex's for commands which tell Phenex to do actions
        String terminatingRegex = "(?i)bye\\s*$";
        String listRegex = "(?i)list\\s*$";
        String markRegex = "^mark \\d+\\s*$";
        String unmarkRegex = "^unmark \\d+\\s*$";

        // regex's for commands which tell Phenex to add Task
        String todoRegex = "^(?i)todo (.+)";
        String deadlineRegex = "^(?i)deadline (.+) /by (.+)";
        String eventRegex = "^(?i)event (.+) /from (.+) /to (.+)$";

        // Patterns and Matchers for each regex
        Pattern terminatingPattern = Pattern.compile(terminatingRegex);
        Pattern listPattern = Pattern.compile(listRegex);
        Pattern markPattern = Pattern.compile(markRegex);
        Pattern unmarkPattern = Pattern.compile(unmarkRegex);
        Pattern todoPattern = Pattern.compile(todoRegex);
        Pattern deadlinePattern = Pattern.compile(deadlineRegex);
        Pattern eventPattern = Pattern.compile(eventRegex);

        Matcher terminatingMatcher;
        Matcher listMatcher;
        Matcher markMatcher;
        Matcher unmarkMatcher;
        Matcher todoMatcher;
        Matcher deadlineMatcher;
        Matcher eventMatcher;

        while (true) {
            // scan inputs
            userInput = scanner.nextLine();
            terminatingMatcher = terminatingPattern.matcher(userInput);

            // detect terminating string
            if (terminatingMatcher.find()) {
                break;
            }

            // Matchers to detect which input
            listMatcher = listPattern.matcher(userInput);
            markMatcher = markPattern.matcher(userInput);
            unmarkMatcher = unmarkPattern.matcher(userInput);
            todoMatcher = todoPattern.matcher(userInput);
            deadlineMatcher = deadlinePattern.matcher(userInput);
            eventMatcher = eventPattern.matcher(userInput);

            p.printLine();

            if (listMatcher.find()) {
                p.printTasks();
            } else if (markMatcher.find()) {
                // mark task as done
                int taskNumber = Integer.parseInt(markMatcher.group().substring(5));
                int idx = taskNumber - 1;
                p.markTaskCompleted(idx);
            } else if (unmarkMatcher.find()) {
                // unmark task as done
                int taskNumber = Integer.parseInt(unmarkMatcher.group().substring(7));
                int idx = taskNumber - 1;
                p.markTaskIncomplete(idx);
            } else if (todoMatcher.matches()) {
                // add ToDo
                p.addTask(todoMatcher, TaskType.TODO);
            } else if (deadlineMatcher.matches()) {
                // add Deadline
                p.addTask(deadlineMatcher, TaskType.DEADLINE);
            } else if (eventMatcher.matches()) {
                // add Event
                p.addTask(eventMatcher, TaskType.EVENT);
            } else {
                System.out.println("\tError, invalid input.");
            }

            p.printLine();
        }

        scanner.close();
        p.sayFarewell();
    }
}
