import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class Phenex {
    public static void main(String[] args) {
        String logo = "  _____    _    _   ______   _   _   ______  __   __ \n"
                + " |  __ \\  | |  | | |  ____| | \\ | | |  ____| \\ \\ / /\n"
                + " | |__) | | |__| | | |__    |  \\| | | |__     \\ V / \n"
                + " | |      | |  | | | |____  | |\\  | | |____   / . \\ \n"
                + " |_|      |_|  |_| |______| |_| \\_| |______| /_/ \\_\\\n";

        String line = "____________________________________________________________ \n";
        String greetMsg = "Hello! I'm \n"
                + logo
                + "Your favourite solid gold mobile suit! \n"
                + line
                + "What can I do for you? \n"
                + line;
        String farewellMsg = "\t Goodbye. Extinguish the Zeon forces on your way out!\n" + line;

        System.out.println(greetMsg);

        // list feature
        Scanner scanner = new Scanner(System.in);

        String terminatingRegex = "(?i)bye\\s*$";
        String listRegex = "(?i)list\\s*$";
        String markRegex = "^mark \\d+\\s*$";
        String unmarkRegex = "^unmark \\d+\\s*$";

        ArrayList<Task> tasks = new ArrayList<>();
        String userInput;
        Pattern terminatingPattern = Pattern.compile(terminatingRegex);
        Pattern listPattern = Pattern.compile(listRegex);
        Pattern markPattern = Pattern.compile(markRegex);
        Pattern unmarkPattern = Pattern.compile(unmarkRegex);

        Matcher terminatingMatcher;
        Matcher listMatcher;
        Matcher markMatcher;
        Matcher unmarkMatcher;

        while (true) {
            userInput = scanner.nextLine();
            terminatingMatcher = terminatingPattern.matcher(userInput);

            // detect terminating string
            if (terminatingMatcher.find()) {
                break;
            }

            listMatcher = listPattern.matcher(userInput);
            markMatcher = markPattern.matcher(userInput);
            unmarkMatcher = unmarkPattern.matcher(userInput);

            System.out.println("\t" + line);

            // detect list string
            if (listMatcher.find()) {
                int size = tasks.size();
                if (size == 0) {
                    System.out.println("\t No scheduled missions. Rest up for the next battle, soldier!");
                    continue;
                }

                System.out.println("\t Outstanding missions: ");
                for (int i = 0; i < size; i++) {
                    String row = "\t "
                            + (i + 1)
                            + "."
                            + tasks.get(i);
                    System.out.println(row);
                }
            } else if (markMatcher.find()) {
                // mark task as done
                int size = tasks.size();
                int taskNumber = Integer.parseInt(markMatcher.group().substring(5));
                int idx = taskNumber - 1;
                if (idx >= size) {
                    System.out.println("\t Invalid input, no such mission!");
                } else {
                    System.out.println("\t Mission marked as complete. Good job, soldier!");
                    Task taskToMark = tasks.get(idx);
                    taskToMark.markComplete();
                    System.out.println("\t\t" + taskToMark);
                }
            } else if (unmarkMatcher.find()) {
                // unmark task as done
                int size = tasks.size();
                int taskNumber = Integer.parseInt(unmarkMatcher.group().substring(7));
                int idx = taskNumber - 1;
                if (idx >= size) {
                    System.out.println("\t Invalid input, no such mission!");
                } else {
                    System.out.println("\t Mission marked as incomplete.");
                    Task taskToUnmark = tasks.get(idx);
                    taskToUnmark.markUncomplete();
                    System.out.println("\t\t" + taskToUnmark);
                }
            } else {
                Task taskToAdd = new Task(userInput);
                tasks.add(taskToAdd);
                String taskAddedMsg = "\t Mission " + userInput + " added.";
                System.out.println(taskAddedMsg);
            }

            System.out.println("\t" + line);
        }

        scanner.close();
        System.out.println(farewellMsg);
    }
}
