package sigma;
import sigma.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// contains the task list
public class TaskList {
    private static List<Task> items;

    public TaskList() {
        items = new ArrayList<>();
    }

    public static List<Task> getItems() {
        return items;
    }
    public static String toPrettyList() {
        int i = 1;
        StringBuilder result = new StringBuilder(); // this is a terrible time complexity
        for (Task item : items) {
            result.append("\n").append(i).append(". ").append(item.toString());
            i++;
        }
        return result.toString();
    }



    public void handleDelete(String userInput) {
        Pattern pattern = Pattern.compile("(delete) (\\d+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            int taskNumber = Integer.parseInt(matcher.group(2)) - 1;
            if (taskNumber >= 0 && taskNumber < items.size()) {
                Task task = items.get(taskNumber);
                items.remove(task);
                System.out.println("task removed:\n" + task.toString() + "\nNow you have " + items.size() + " tasks in the list");
            }
        }
    }

    // why do i even need this
    public static void addToList(Task task) {
        items.add(task);
    }

    public static int getSize() {
        return items.size();
    }
}
