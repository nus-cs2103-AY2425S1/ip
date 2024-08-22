import java.util.*;
import java.util.regex.*;

public class Sigma {
    public static ArrayList<Task> items;

    public static String toPrettyList(List<Task> itemsArray) {
        StringBuilder result = new StringBuilder(); // this is a terrible time complexity
        for (int i = 0; i < itemsArray.size(); i++) {
            result.append(i + 1).append(". ").append(itemsArray.get(i).toString()).append("\n");
        }
        return result.toString();
    }

    public static void handleMarkUnmark(String userInput) {
        Pattern pattern = Pattern.compile("(mark|unmark) (\\d+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String action = matcher.group(1);
            int taskNumber = Integer.parseInt(matcher.group(2)) - 1;

            if (taskNumber >= 0 && taskNumber < items.size()) {
                Task task = items.get(taskNumber);
                if (action.equals("mark")) {
                    task.setStatus(true);
                    System.out.println("task marked as done:\n" + task);
                } else if (action.equals("unmark")) {
                    task.setStatus(false);
                    System.out.println("task unmarked:\n" + task);
                }
            }
        }
    }

    public static void main(String[] args) {
        items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String welcomeMessage = "Hello! I'm Sigma \nWhat can I do for you? \n";
        System.out.println(welcomeMessage);

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.contains("list")) {
                System.out.println("Here are your tasks:\n" + toPrettyList(items));
                continue;
            }
            if (userInput.contains("bye")) {
                break;
            }

            if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                handleMarkUnmark(userInput);
                continue;
            }
            Task task = new Task(userInput, false);
            items.add(task);
            System.out.println("added: " + userInput);
        }
        System.out.println("Bye! See you soon");
    }
}
