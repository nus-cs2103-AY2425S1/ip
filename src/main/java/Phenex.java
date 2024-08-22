import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class Phenex {
    private final String line = "\t____________________________________________________________\n";
    private final ArrayList<Task> tasks;
    private final String logo = "  _____    _    _   ______   _   _   ______  __   __ \n"
            + " |  __ \\  | |  | | |  ____| | \\ | | |  ____| \\ \\ / /\n"
            + " | |__) | | |__| | | |__    |  \\| | | |__     \\ V / \n"
            + " | |      | |  | | | |____  | |\\  | | |____   / . \\ \n"
            + " |_|      |_|  |_| |______| |_| \\_| |______| /_/ \\_\\\n";
    private final String greetMsg = "Hello! I'm \n"
            + logo
            + "Your favourite solid gold mobile suit! \n"
            + line
            + "What can I do for you? \n"
            + line;
    private final String farewellMsg = "\t Goodbye. Extinguish the Zeon forces on your way out!\n" + line;

    public Phenex() {
        this.tasks = new ArrayList<>();
    }

    public void greet() {
        System.out.println(greetMsg);
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

        System.out.println("\t Outstanding missions: ");
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

    public static void main(String[] args) {
        Phenex p = new Phenex();
        p.greet();

        // list feature
        Scanner scanner = new Scanner(System.in);

        // regex's for commands which tell Phenex to do actions
        String terminatingRegex = "(?i)bye\\s*$";
        String listRegex = "(?i)list\\s*$";
        String markRegex = "^mark \\d+\\s*$";
        String unmarkRegex = "^unmark \\d+\\s*$";

        // regex's for commands which tell Phenex to add Task
        String todoRegex = "^(?i)todo (.+)";
        String deadlineRegex = "^(?i)deadline (.+) /by (.+)";
        String eventRegex = "^(?i)event (.+) /from (.+) /to (.+)$";

        ArrayList<Task> tasks = new ArrayList<>();
        String userInput;
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
                // add new tasks
                String todoName = todoMatcher.group(1);
                ToDo toDo = new ToDo(todoName);
                p.tasks.add(toDo);
                String taskAddedMsg = "\t Mission " + todoName + " added:";
                System.out.println(taskAddedMsg);
                System.out.println("\t   " + toDo);
                System.out.println("\t Total upcoming missions: " + p.tasks.size());
            } else if (deadlineMatcher.matches()) {
                String deadlineName = deadlineMatcher.group(1);
                String deadlineBy = deadlineMatcher.group(2);
                Deadline deadline = new Deadline(deadlineName, deadlineBy);
                p.tasks.add(deadline);
                String taskAddedMsg = "\t Mission " + deadlineName + " added:";
                System.out.println(taskAddedMsg);
                System.out.println("\t   " + deadline);
                System.out.println("\t Total upcoming missions: " + p.tasks.size());
            } else if (eventMatcher.matches()) {
                String eventName = eventMatcher.group(1);
                String eventFrom = eventMatcher.group(2);
                String eventTo = eventMatcher.group(3);
                Event event = new Event(eventName, eventFrom, eventTo);
                p.tasks.add(event);
                String taskAddedMsg = "\t Mission " + eventName + " added:";
                System.out.println(taskAddedMsg);
                System.out.println("\t   " + event);
                System.out.println("\t Total upcoming missions: " + p.tasks.size());
            } else {
                System.out.println("Error, invalid input.");
            }

            p.printLine();
        }

        scanner.close();
        p.sayFarewell();
    }
}
